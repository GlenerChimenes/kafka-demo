package com.example.dockbank.service;

import com.example.dockbank.dto.ContaDTO;
import com.example.dockbank.dto.TransacaoDTO;
import com.example.dockbank.exception.BusinessException;
import com.example.dockbank.exception.NotFoundException;
import com.example.dockbank.model.Conta;
import com.example.dockbank.model.StatusConta;
import com.example.dockbank.model.Transacao;
import com.example.dockbank.repository.ContaRepository;
import com.example.dockbank.repository.PortadorRepository;
import com.example.dockbank.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final PortadorRepository portadorRepository;
    private final TransacaoRepository transacaoRepository;

    private static final BigDecimal LIMITE_DIARIO = new BigDecimal("2000.00");

    @Transactional
    public ContaDTO criarConta(String cpfPortador) {
        String cpf = cpfPortador.replaceAll("\\D", "");
        // verifica portador
        portadorRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Portador não encontrado"));

        String numero;
        int tentativas = 0;
        do {
            numero = gerarNumeroConta();
            tentativas++;
            if (tentativas > 50) throw new BusinessException("Não foi possível gerar número de conta único");
        } while (contaRepository.findByNumero(numero).isPresent());

        Conta c = Conta.builder()
                .cpfPortador(cpf)
                .numero(numero)
                .agencia("0001")
                .saldo(BigDecimal.ZERO)
                .status(StatusConta.ATIVA)
                .bloqueada(false)
                .build();

        Conta saved = contaRepository.save(c);
        return toDto(saved);
    }

    @Transactional(readOnly = true)
    public ContaDTO consultar(String numero) {
        Conta c = contaRepository.findByNumero(numero)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));
        return toDto(c);
    }

    @Transactional(readOnly = true)
    public List<TransacaoDTO> consultarExtrato(String numero, String inicioIso, String fimIso) {
        OffsetDateTime inicio = OffsetDateTime.parse(inicioIso);
        OffsetDateTime fim = OffsetDateTime.parse(fimIso);
        List<Transacao> lista = transacaoRepository.findByContaNumeroAndDataHoraBetweenOrderByDataHoraDesc(numero, inicio, fim);
        return lista.stream()
                .map(t -> new TransacaoDTO(t.getId(), t.getContaNumero(), t.getTipo(), t.getValor(), t.getDataHora(), t.getDescricao()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void depositar(String numeroConta, BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) throw new BusinessException("Valor de depósito deve ser positivo");

        Conta c = contaRepository.findByNumero(numeroConta)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));

        if (c.getStatus() != StatusConta.ATIVA) throw new BusinessException("Conta não está ativa");
        if (c.isBloqueada()) throw new BusinessException("Conta está bloqueada");

        c.setSaldo(c.getSaldo().add(valor));
        contaRepository.save(c);

        Transacao t = Transacao.builder()
                .contaNumero(numeroConta)
                .tipo("DEPOSITO")
                .valor(valor)
                .dataHora(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS))
                .descricao("Depósito via API")
                .build();
        transacaoRepository.save(t);
    }

    @Transactional
    public void sacar(String numeroConta, BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) throw new BusinessException("Valor de saque deve ser positivo");

        Conta c = contaRepository.findByNumero(numeroConta)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));

        if (c.getStatus() != StatusConta.ATIVA) throw new BusinessException("Conta não está ativa");
        if (c.isBloqueada()) throw new BusinessException("Conta está bloqueada");

        if (c.getSaldo().compareTo(valor) < 0) throw new BusinessException("Saldo insuficiente");

        // calcula saques já feitos no dia corrente
        OffsetDateTime inicio = OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS);
        OffsetDateTime fim = inicio.plusDays(1).minusNanos(1);
        BigDecimal jaSacado = transacaoRepository.somaSaquesNoPeriodo(numeroConta, inicio, fim);
        if (jaSacado == null) jaSacado = BigDecimal.ZERO;

        if (jaSacado.add(valor).compareTo(LIMITE_DIARIO) > 0) {
            throw new BusinessException("Limite diário de saque excedido");
        }

        // aplica saque
        BigDecimal novoSaldo = c.getSaldo().subtract(valor);
        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            // regra: nunca saldo negativo
            throw new BusinessException("Operação inválida: saldo não pode ficar negativo");
        }

        c.setSaldo(novoSaldo);
        contaRepository.save(c);

        Transacao t = Transacao.builder()
                .contaNumero(numeroConta)
                .tipo("SAQUE")
                .valor(valor)
                .dataHora(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS))
                .descricao("Saque via API")
                .build();
        transacaoRepository.save(t);
    }

    @Transactional
    public void bloquear(String numeroConta) {
        Conta c = contaRepository.findByNumero(numeroConta)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));
        if (c.getStatus() != StatusConta.ATIVA) throw new BusinessException("Conta não está ativa");
        c.setBloqueada(true);
        contaRepository.save(c);
    }

    @Transactional
    public void desbloquear(String numeroConta) {
        Conta c = contaRepository.findByNumero(numeroConta)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));
        if (c.getStatus() != StatusConta.ATIVA) throw new BusinessException("Conta não está ativa");
        c.setBloqueada(false);
        contaRepository.save(c);
    }

    @Transactional
    public void fecharConta(String numeroConta) {
        Conta c = contaRepository.findByNumero(numeroConta)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));
        if (c.getStatus() != StatusConta.ATIVA) throw new BusinessException("Conta já está fechada");
        if (c.getSaldo().compareTo(BigDecimal.ZERO) != 0) throw new BusinessException("Só é permitido fechar conta com saldo zero");
        c.setStatus(StatusConta.FECHADA);
        contaRepository.save(c);
    }

    private ContaDTO toDto(Conta c) {
        return new ContaDTO(c.getId(), c.getCpfPortador(), c.getNumero(), c.getAgencia(), c.getSaldo(), c.getStatus(), c.isBloqueada());
    }

    private String gerarNumeroConta() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rnd.nextInt(10));
        }
        return sb.toString();
    }
}



package com.example.dockbank.service;

import com.example.dockbank.dto.PortadorDTO;
import com.example.dockbank.exception.BusinessException;
import com.example.dockbank.exception.NotFoundException;
import com.example.dockbank.model.Portador;
import com.example.dockbank.model.Role;
import com.example.dockbank.model.StatusConta;
import com.example.dockbank.projections.UserDetailsProjection;
import com.example.dockbank.repository.ContaRepository;
import com.example.dockbank.repository.PortadorRepository;
import com.example.dockbank.util.CpfValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortadorService implements UserDetailsService {

    private final PortadorRepository portadorRepository;
    private final ContaRepository contaRepository;

    @Transactional
    public PortadorDTO criar(PortadorDTO dto) {
        if (dto.getCpf() == null || !CpfValidator.isValid(dto.getCpf())) {
            throw new BusinessException("CPF inválido");
        }
        String cpfClean = dto.getCpf().replaceAll("\\D", "");
        if (portadorRepository.existsByCpf(cpfClean)) {
            throw new BusinessException("CPF já cadastrado");
        }

        Portador p = Portador.builder()
                .nomeCompleto(dto.getNomeCompleto())
                .cpf(cpfClean)
                .build();

        Portador saved = portadorRepository.save(p);
        return new PortadorDTO(saved.getId(), saved.getNomeCompleto(), saved.getCpf());
    }

    @Transactional(readOnly = true)
    public List<PortadorDTO> listar() {
        return portadorRepository.findAll().stream()
                .map(p -> new PortadorDTO(p.getId(), p.getNomeCompleto(), p.getCpf()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void remover(String cpf) {
        String cpfClean = cpf.replaceAll("\\D", "");
        Portador p = portadorRepository.findByCpf(cpfClean)
                .orElseThrow(() -> new NotFoundException("Portador não encontrado"));

        // Só permite remover se não tiver conta ativa
        boolean temAtiva = contaRepository.findByCpfPortadorAndStatus(cpfClean, StatusConta.ATIVA)
                .stream().findAny().isPresent();
        if (temAtiva) {
            throw new BusinessException("Não é possível remover portador com conta(s) ativa(s)");
        }

        portadorRepository.deleteByCpf(cpfClean);
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = portadorRepository.searchUserAndRolesByEmail(cpf);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        Portador portador = new Portador();
        portador.setCpf(cpf);
        portador.setPassword(result.getFirst().getPassword());
        for (UserDetailsProjection projection : result) {
            portador.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return portador;
    }
}


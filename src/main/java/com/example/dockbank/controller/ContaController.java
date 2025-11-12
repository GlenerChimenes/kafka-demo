package com.example.dockbank.controller;

import com.example.dockbank.dto.ContaDTO;
import com.example.dockbank.dto.TransacaoDTO;
import com.example.dockbank.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/{cpfPortador}")
    public ResponseEntity<ContaDTO> criarConta(@PathVariable String cpfPortador) {
        return ResponseEntity.ok(contaService.criarConta(cpfPortador));
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @GetMapping("/{numero}")
    public ResponseEntity<ContaDTO> consultar(@PathVariable String numero) {
        return ResponseEntity.ok(contaService.consultar(numero));
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @GetMapping("/{numero}/extrato")
    public ResponseEntity<List<TransacaoDTO>> consultarExtrato(
            @PathVariable String numero,
            @RequestParam String inicio,
            @RequestParam String fim) {
        return ResponseEntity.ok(contaService.consultarExtrato(numero, inicio, fim));
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @PostMapping("/{numero}/deposito")
    public ResponseEntity<Void> depositar(@PathVariable String numero, @RequestBody TransacaoDTO dto) {
        contaService.depositar(numero, dto.getValor());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @PostMapping("/{numero}/saque")
    public ResponseEntity<Void> sacar(@PathVariable String numero, @RequestBody TransacaoDTO dto) {
        contaService.sacar(numero, dto.getValor());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @PostMapping("/{numero}/bloquear")
    public ResponseEntity<Void> bloquear(@PathVariable String numero) {
        contaService.bloquear(numero);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @PostMapping("/{numero}/desbloquear")
    public ResponseEntity<Void> desbloquear(@PathVariable String numero) {
        contaService.desbloquear(numero);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> fecharConta(@PathVariable String numero) {
        contaService.fecharConta(numero);
        return ResponseEntity.noContent().build();
    }
}

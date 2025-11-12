package com.example.dockbank.controller;

import com.example.dockbank.dto.PortadorDTO;
import com.example.dockbank.service.PortadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portadores")
public class PortadorController {

    private final PortadorService portadorService;

    public PortadorController(PortadorService portadorService) {
        this.portadorService = portadorService;
    }

    @PostMapping
    public ResponseEntity<PortadorDTO> criar(@RequestBody PortadorDTO dto) {
        return ResponseEntity.ok(portadorService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<PortadorDTO>> listar() {
        return ResponseEntity.ok(portadorService.listar());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> remover(@PathVariable String cpf) {
        portadorService.remover(cpf);
        return ResponseEntity.noContent().build();
    }
}


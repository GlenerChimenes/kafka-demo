package com.example.dockbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortadorDTO {
    private Long id;
    private String nomeCompleto;
    private String cpf;
}


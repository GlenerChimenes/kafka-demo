package com.example.dockbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO {
    private Long id;
    private String contaNumero;
    private String tipo; // "DEPOSITO" ou "SAQUE"
    private BigDecimal valor;
    private OffsetDateTime dataHora;
    private String descricao;
}

package com.example.dockbank.dto;

import com.example.dockbank.model.StatusConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {
    private Long id;
    private String cpfPortador;
    private String numero;
    private String agencia;
    private BigDecimal saldo;
    private StatusConta status;
    private boolean bloqueada;
}

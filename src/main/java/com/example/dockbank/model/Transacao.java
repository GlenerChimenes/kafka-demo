package com.example.dockbank.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transacoes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contaNumero;

    @Column(nullable = false)
    private String tipo; // DEPOSITO ou SAQUE

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private OffsetDateTime dataHora;

    private String descricao;
}

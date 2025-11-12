package com.example.dockbank.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "contas", uniqueConstraints = @UniqueConstraint(columnNames = {"numero", "agencia"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    private String cpfPortador;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private String agencia;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private StatusConta status;

    private boolean bloqueada;

}

package com.example.dockbank.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "portadores", uniqueConstraints = @UniqueConstraint(columnNames = "cpf"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Portador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;
}

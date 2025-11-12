package com.example.dockbank.repository;

import com.example.dockbank.model.Portador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortadorRepository extends JpaRepository<Portador, Long> {

    boolean existsByCpf(String cpf);

    Optional<Portador> findByCpf(String cpf);

    void deleteByCpf(String cpf);
}

package com.example.dockbank.repository;

import com.example.dockbank.model.Conta;
import com.example.dockbank.model.StatusConta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByNumero(String numero);

    Optional<Conta> findByCpfPortadorAndStatus(String cpf, StatusConta status);

    List<Conta> findByCpfPortador(String cpf);
}

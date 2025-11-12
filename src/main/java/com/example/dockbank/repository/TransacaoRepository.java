package com.example.dockbank.repository;

import com.example.dockbank.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("select sum(t.valor) from Transacao t where t.contaNumero = :num and t.tipo = 'SAQUE' and t.dataHora >= :inicio and t.dataHora <= :fim")
    BigDecimal somaSaquesNoPeriodo(@Param("num") String num, @Param("inicio") OffsetDateTime inicio, @Param("fim") OffsetDateTime fim);

    List<Transacao> findByContaNumeroAndDataHoraBetweenOrderByDataHoraDesc(String contaNumero, OffsetDateTime inicio, OffsetDateTime fim);
}


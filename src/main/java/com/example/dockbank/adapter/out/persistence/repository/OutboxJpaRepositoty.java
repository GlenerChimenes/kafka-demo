package com.example.dockbank.adapter.out.persistence.repository;

import com.example.dockbank.adapter.out.persistence.entity.OutboxEvent;
import com.example.dockbank.adapter.out.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxJpaRepositoty extends JpaRepository<OutboxEvent, Long> {

    List<OutboxEvent> findByProcessedFalse();

}

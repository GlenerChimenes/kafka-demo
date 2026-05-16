package com.example.dockbank.adapter.out.persistence.adapter;

import com.example.dockbank.adapter.out.persistence.entity.PedidoEntity;
import com.example.dockbank.adapter.out.persistence.mapper.PedidoMapper;
import com.example.dockbank.adapter.out.persistence.repository.OutboxJpaRepositoty;
import com.example.dockbank.adapter.out.persistence.repository.PedidoJpaRepository;
import com.example.dockbank.domain.model.Pedido;
import com.example.dockbank.domain.port.EventPublisherPort;
import com.example.dockbank.domain.port.OutboxRepositoryPort;
import com.example.dockbank.domain.port.PedidoRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class OutboxRepositoryAdapter implements OutboxRepositoryPort {

    private final OutboxJpaRepositoty repository;

    public OutboxRepositoryAdapter(PedidoJpaRepository pedidoJpaRepository, OutboxJpaRepositoty repository) {
        this.repository = repository;
    }


}

package com.example.dockbank.adapter.in.kafka;

import com.example.dockbank.adapter.out.persistence.entity.PedidoEntity;
import com.example.dockbank.domain.event.PedidoCriadoEvent;
import com.example.dockbank.domain.model.Pedido;
import com.example.dockbank.domain.port.PedidoRepositoryPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    private final PedidoRepositoryPort repository;

    public PedidoConsumer(PedidoRepositoryPort repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "pedido-criado", groupId = "grupo-teste")
    public void consumir(PedidoCriadoEvent event) {
        System.out.println("Evento recebido: " + event);
        Pedido pedido = repository.PagarPedido(event.id());
        System.out.println("Pedido pago: " + pedido.getId());
    }
}

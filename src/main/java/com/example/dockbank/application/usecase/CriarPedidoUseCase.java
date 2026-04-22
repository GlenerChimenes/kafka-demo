package com.example.dockbank.application.usecase;

import com.example.dockbank.domain.event.PedidoCriadoEvent;
import com.example.dockbank.domain.model.Pedido;
import com.example.dockbank.domain.port.EventPublisherPort;
import com.example.dockbank.domain.port.PedidoRepositoryPort;

public class CriarPedidoUseCase {

    private final PedidoRepositoryPort repository;

    private final EventPublisherPort eventPublish;

    public CriarPedidoUseCase(PedidoRepositoryPort repository, EventPublisherPort eventPublish) {
        this.repository = repository;
        this.eventPublish = eventPublish;
    }

    public void executar(Double valor){
        Pedido pedido = new Pedido(valor);

        Pedido pedidoSalvo = repository.salvar(pedido);

        PedidoCriadoEvent evento = new PedidoCriadoEvent(pedidoSalvo.getId(), pedidoSalvo.getValor());

        eventPublish.publicar("pedido-criado", evento);
    }
}

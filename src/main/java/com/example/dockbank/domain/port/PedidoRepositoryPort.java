package com.example.dockbank.domain.port;

import com.example.dockbank.domain.model.Pedido;

public interface PedidoRepositoryPort {

    Pedido salvar(Pedido pedido);

    Pedido buscaPorId(long id);

    Pedido PagarPedido(Long id);
}

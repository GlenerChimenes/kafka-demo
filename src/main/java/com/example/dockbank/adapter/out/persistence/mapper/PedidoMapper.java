package com.example.dockbank.adapter.out.persistence.mapper;

import com.example.dockbank.adapter.out.persistence.entity.PedidoEntity;
import com.example.dockbank.domain.model.Pedido;

public class PedidoMapper {

    public static PedidoEntity toEntity(Pedido pedido) {
        var entity = new PedidoEntity();
        entity.setValor(pedido.getValor());
        entity.setPago(false);
        return entity;
    }
    public static Pedido toDomain(PedidoEntity entity){
        Pedido pedido = new Pedido(entity.getValor());
        pedido.setId(entity.getId());
        return pedido;
    }
}

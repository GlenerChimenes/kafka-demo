package com.example.dockbank.adapter.out.persistence.adapter;

import com.example.dockbank.adapter.out.persistence.entity.PedidoEntity;
import com.example.dockbank.adapter.out.persistence.mapper.PedidoMapper;
import com.example.dockbank.adapter.out.persistence.repository.PedidoJpaRepository;
import com.example.dockbank.domain.model.Pedido;
import com.example.dockbank.domain.port.PedidoRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository pedidoJpaRepository;

    public PedidoRepositoryAdapter(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        var entity = PedidoMapper.toEntity(pedido);
        var entitySalva = pedidoJpaRepository.save(entity);
        return PedidoMapper.toDomain(entitySalva);
    }

    @Override
    public Pedido buscaPorId(long id) {
        PedidoEntity entity = pedidoJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrato: " + id));
        return PedidoMapper.toDomain(entity);
    }

    @Override
    public Pedido PagarPedido(Long id) {
        PedidoEntity entity = pedidoJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrato: " + id));
        entity.setPago(true);
        var entitySalva = pedidoJpaRepository.save(entity);
        return PedidoMapper.toDomain(entitySalva);
    }
}

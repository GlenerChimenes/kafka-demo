package com.example.dockbank.config;

import com.example.dockbank.application.usecase.CriarPedidoUseCase;
import com.example.dockbank.domain.port.EventPublisherPort;
import com.example.dockbank.domain.port.PedidoRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CriarPedidoUseCase criarPedidoUseCase(PedidoRepositoryPort repository, EventPublisherPort publisher) {
        return new CriarPedidoUseCase(repository, publisher);
    }
}

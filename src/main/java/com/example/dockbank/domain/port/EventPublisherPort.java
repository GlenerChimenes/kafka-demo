package com.example.dockbank.domain.port;

public interface EventPublisherPort {
    void publicar(String topico, Object evento);

}

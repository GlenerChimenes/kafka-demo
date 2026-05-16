package com.example.dockbank.adapter.out.messaging;

import com.example.dockbank.adapter.out.persistence.entity.OutboxEvent;
import com.example.dockbank.adapter.out.persistence.repository.OutboxJpaRepositoty;
import com.example.dockbank.domain.port.EventPublisherPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventoPublisherAdapter implements EventPublisherPort {

    private final OutboxJpaRepositoty outboxJpaRepositoty;
    private final ObjectMapper objectMapper;

    public EventoPublisherAdapter(OutboxJpaRepositoty outboxJpaRepositoty, ObjectMapper objectMapper) {
        this.outboxJpaRepositoty = outboxJpaRepositoty;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publicar(String topico, Object evento) {
        try {
            String payload = objectMapper.writeValueAsString(evento);

            OutboxEvent event = new OutboxEvent();
            event.setTopic(topico);
            event.setPayload(payload);
            event.setProcessed(false);
            outboxJpaRepositoty.save(event);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

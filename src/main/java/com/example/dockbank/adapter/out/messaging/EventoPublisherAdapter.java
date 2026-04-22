package com.example.dockbank.adapter.out.messaging;

import com.example.dockbank.adapter.out.persistence.entity.OutboxEvent;
import com.example.dockbank.adapter.out.persistence.repository.OutboxJpaRepositoty;
import com.example.dockbank.domain.port.EventPublisherPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventoPublisherAdapter implements EventPublisherPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EventoPublisherAdapter(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicar(String topico, Object evento) {
        kafkaTemplate.send(topico, evento);
//        OutboxEvent event = new OutboxEvent();
//        event.setTopic(topico);
//        event.setPayload(payload);
//        event.setProcessed(false);
//        repository.save(event);
    }
}

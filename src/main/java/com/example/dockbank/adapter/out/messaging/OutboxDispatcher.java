package com.example.dockbank.adapter.out.messaging;

import com.example.dockbank.adapter.out.persistence.entity.OutboxEvent;
import com.example.dockbank.adapter.out.persistence.repository.OutboxJpaRepositoty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OutboxDispatcher {

    private final OutboxJpaRepositoty repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OutboxDispatcher(OutboxJpaRepositoty repository, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void dispatch() {

        List<OutboxEvent> eventos = repository.findByProcessedFalse();

        for (OutboxEvent e : eventos) {
            kafkaTemplate.send(e.getTopic(), e.getPayload());

            e.setProcessed(true);
            repository.save(e);
        }
    }

}

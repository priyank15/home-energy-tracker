package com.priyankdevurkar.telemetry_service.service;

import com.priyankdevurkar.telemetry_service.dto.EnergyUsageDto;
import com.priyankdevurkar.kafka.event.EnergyUsageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TelemetryService {


    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

    public TelemetryService(KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void ingestEnergyUsage(EnergyUsageDto input) {
        // Convert DTO to Event
        EnergyUsageEvent event = EnergyUsageEvent.builder()
                .deviceId(input.deviceId())
                .energyConsumed(input.energyConsumed())
                .timestamp(input.timestamp())
                .build();

        //Send to the Kafka Topic
        kafkaTemplate.send("energy-usage",event);
        log.info("Ingested Energy Usage Event: {}", event);
    }
}

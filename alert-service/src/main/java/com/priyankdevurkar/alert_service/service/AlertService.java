package com.priyankdevurkar.alert_service.service;

import com.priyankdevurkar.kafka.event.AlertingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlertService {

    private EmailService emailService;

    public AlertService(EmailService emailService) {
        this.emailService = emailService;
    }
    @KafkaListener(topics="energy-alerts", groupId = "alert-service")
    public void EnergyUsageAlertEvent(AlertingEvent alertingEvent){
        log.info("Received alert event: {} ", alertingEvent);

        //send email alert
        final String subject = "Energy Usage Alert for User "
                + alertingEvent.getUserId();

        final String message = "ALERT!: " + alertingEvent.getMessage() +
                "\nThreshold: " + alertingEvent.getThreshold() +
                "\nEnergy Consumed: " + alertingEvent.getEnergyConsumed();
        emailService.sendEmail(alertingEvent.getEmail(),
                subject,
                message,
                alertingEvent.getUserId());

    }
}

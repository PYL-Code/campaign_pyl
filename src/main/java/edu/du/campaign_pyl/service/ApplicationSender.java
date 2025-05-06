package edu.du.campaign_pyl.service;

import edu.du.campaign_pyl.config.RabbitMQConfig;
import edu.du.campaign_pyl.entity.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationSender {

    private final RabbitTemplate rabbitTemplate;

    public void sendApplication(Application application) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                application
        );
    }
}

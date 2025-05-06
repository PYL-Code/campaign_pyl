package edu.du.campaign_pyl.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USERS_QUEUE = "users.queue";
    public static final String CAMPAIGNS_QUEUE = "campaigns.queue";

    @Bean
    public Queue usersQueue() {
        return new Queue(USERS_QUEUE, true); // durable = true
    }

    @Bean
    public Queue campaignsQueue() {
        return new Queue(CAMPAIGNS_QUEUE, true);
    }
}

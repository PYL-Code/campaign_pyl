package edu.du.campaign_pyl.service;

import edu.du.campaign_pyl.config.RabbitMQConfig;
import edu.du.campaign_pyl.entity.Campaign;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CampaignSender {

    private final RabbitTemplate rabbitTemplate;

    public CampaignSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCampaign(Campaign campaign) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.CAMPAIGNS_QUEUE, campaign);
    }
}

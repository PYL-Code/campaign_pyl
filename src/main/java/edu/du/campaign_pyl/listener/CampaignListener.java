package edu.du.campaign_pyl.listener;

import edu.du.campaign_pyl.entity.Campaign;
import edu.du.campaign_pyl.repository.CampaignRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CampaignListener {

    private final CampaignRepository campaignRepository;

    public CampaignListener(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @RabbitListener(queues = "campaigns.queue")
    public void receiveCampaign(Campaign campaign) {
        campaignRepository.save(campaign);
    }
}

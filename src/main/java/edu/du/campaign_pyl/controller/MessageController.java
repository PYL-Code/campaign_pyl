package edu.du.campaign_pyl.controller;

import edu.du.campaign_pyl.entity.Campaign;
import edu.du.campaign_pyl.service.CampaignSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final CampaignSender campaignSender;

    public MessageController(CampaignSender campaignSender) {
        this.campaignSender = campaignSender;
    }

    @PostMapping("/send-campaign")
    public String sendCampaign(@RequestBody Campaign campaign) {
        campaignSender.sendCampaign(campaign);
        return "Campaign message sent!";
    }
}

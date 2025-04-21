package edu.du.campaign_pyl.service;

import edu.du.campaign_pyl.entity.Campaign;
import edu.du.campaign_pyl.repository.CampaignRepository;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public Campaign findById(Long id) {
        return campaignRepository.findById(id).orElse(null);
    }
}

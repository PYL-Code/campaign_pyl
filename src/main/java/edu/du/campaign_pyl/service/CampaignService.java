package edu.du.campaign_pyl.service;

import edu.du.campaign_pyl.entity.Campaign;
import edu.du.campaign_pyl.entity.enums.ProgressStatus;
import edu.du.campaign_pyl.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public Campaign findById(Long id) {
        return campaignRepository.findById(id).orElse(null);
    }

    public void addCount(Long campaignNo) {
        campaignRepository.incrementCurrentCount(campaignNo);
    }

    private ProgressStatus getProgressStatus(Campaign campaign) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(campaign.getEndDate()) && now.isBefore(campaign.getEndDate().plusDays(7))) {
            return ProgressStatus.REVIEWING;
        } else if (now.isAfter(campaign.getEndDate().plusDays(7))) {
            return ProgressStatus.COMPLETED;
        }
        return campaign.getProgressStatus();
    }
}

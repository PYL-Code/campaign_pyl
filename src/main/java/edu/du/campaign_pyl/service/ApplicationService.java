package edu.du.campaign_pyl.service;

import edu.du.campaign_pyl.entity.Application;
import edu.du.campaign_pyl.repository.ApplicationRepository;
import edu.du.campaign_pyl.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CampaignRepository campaignRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              CampaignRepository campaignRepository) {
        this.applicationRepository = applicationRepository;
        this.campaignRepository = campaignRepository;
    }

    @Transactional
    public void apply(Application application) {
        applicationRepository.save(application);

        Long campaignNo = application.getCampaign().getCampaignNo();
        campaignRepository.incrementCurrentCount(campaignNo);
    }

    public Application getApplication(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }
}

package edu.du.campaign_pyl.service;

import edu.du.campaign_pyl.entity.Application;
import edu.du.campaign_pyl.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void save(Application application) {
        applicationRepository.save(application);
    }
}

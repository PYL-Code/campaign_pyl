package edu.du.campaign_pyl.repository;

import edu.du.campaign_pyl.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}

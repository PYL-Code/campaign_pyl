package edu.du.campaign_pyl.repository;

import edu.du.campaign_pyl.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}

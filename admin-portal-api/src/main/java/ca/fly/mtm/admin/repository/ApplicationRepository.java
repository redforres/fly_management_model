package ca.fly.mtm.admin.repository;

import ca.fly.mtm.admin.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByApplicant_Id(Long applicantId);
}

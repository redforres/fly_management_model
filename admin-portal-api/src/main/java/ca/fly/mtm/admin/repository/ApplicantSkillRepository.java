package ca.fly.mtm.admin.repository;

import ca.fly.mtm.admin.entity.ApplicantSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantSkillRepository extends JpaRepository<ApplicantSkill, Long> {
    List<ApplicantSkill> findByIdApplicantId(Long applicantId);
}

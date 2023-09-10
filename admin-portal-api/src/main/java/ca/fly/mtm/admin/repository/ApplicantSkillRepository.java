package ca.fly.mtm.admin.repository;

import ca.fly.mtm.admin.entity.ApplicantSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApplicantSkillRepository extends JpaRepository<ApplicantSkill, Long> {
    List<ApplicantSkill> findByIdApplicantId(Long applicantId);

    @Transactional
    void deleteAllByIdApplicantId(Long applicantId);
}

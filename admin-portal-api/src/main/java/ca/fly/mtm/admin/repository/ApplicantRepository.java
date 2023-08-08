package ca.fly.mtm.admin.repository;

import ca.fly.mtm.admin.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}

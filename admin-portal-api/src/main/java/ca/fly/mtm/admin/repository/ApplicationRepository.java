package ca.fly.mtm.admin.repository;

import ca.fly.mtm.admin.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}

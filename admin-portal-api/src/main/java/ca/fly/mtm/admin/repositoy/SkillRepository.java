package ca.fly.mtm.admin.repositoy;

import ca.fly.mtm.admin.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}

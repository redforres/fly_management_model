package ca.fly.mtm.admin.repository;

import ca.fly.mtm.admin.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}

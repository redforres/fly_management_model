package ca.fly.mtm.admin.repository;

import ca.fly.mtm.admin.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByApplication_Id(Long applicantId);
}

package ca.fly.mtm.admin.service;

import ca.fly.mtm.admin.entity.Document;
import ca.fly.mtm.admin.model.DocumentDTO;
import ca.fly.mtm.admin.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Boolean existsById(final Long id) {
        return documentRepository.existsById(id);
    }

    public List<DocumentDTO> getAll() {
        return documentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .sorted(Comparator.comparing(DocumentDTO::getId)) // sort by ID
                .collect(Collectors.toList());
    }

    public DocumentDTO getById(final Long id) {
        return documentRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find document with id=" + id));
    }

    public List<DocumentDTO> getByApplicationId(final Long applicationId) {
        return documentRepository.findByApplication_Id(applicationId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public DocumentDTO create(final DocumentDTO documentDTO) {
        Document document = new Document();
        mapToEntity(documentDTO, document);
        return mapToDTO(documentRepository.save(document));
    }

    public void update(DocumentDTO documentDTO) {
        final Document document = new Document();
        mapToEntity(documentDTO, document);
        documentRepository.save(document);
    }

    public void delete(final Long id) {
        try {
            documentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Cannot find document with id=" + id);
        }
    }

    private DocumentDTO mapToDTO(Document document) {
        DocumentDTO.Builder builder = DocumentDTO.builder()
                .id(document.getId())
                .content(document.getContent())
                .createdBy(document.getCreatedBy())
                .modifiedBy(document.getModifiedBy())
                .createdAt(document.getCreatedAt())
                .modifiedAt(document.getModifiedAt());

        return builder.build();
    }

    @SuppressWarnings("UnusedReturnValue")
    private Document mapToEntity(DocumentDTO documentDTO, Document document) {

        if (documentDTO.getContent() != null) {
            document.setContent(documentDTO.getContent());
        }

        if (documentDTO.getCreatedBy() != null) {
            document.setCreatedBy(documentDTO.getCreatedBy());
        }

        if (documentDTO.getModifiedBy() != null) {
            document.setModifiedBy(documentDTO.getModifiedBy());
        }

        if (documentDTO.getCreatedAt() != null) {
            document.setCreatedAt(documentDTO.getCreatedAt());
        }

        if (documentDTO.getModifiedAt() != null) {
            document.setModifiedAt(documentDTO.getModifiedAt());
        }

        return document;
    }
}

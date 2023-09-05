package ca.fly.mtm.admin.service;

import ca.fly.mtm.admin.entity.Document;
import ca.fly.mtm.admin.model.DocumentDTO;
import ca.fly.mtm.admin.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<DocumentDTO> getAll(Pageable pageable) {
        return documentRepository.findAll(pageable)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public DocumentDTO getById(final Long id) {
        return documentRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DocumentDTO documentDTO) {
        Document document = new Document();
        mapToEntity(documentDTO, document);
        return documentRepository.save(document).getId();
    }

    public void update(final Long id, DocumentDTO documentDTO) {
        final Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(documentDTO, document);
        documentRepository.save(document);
    }

    public void delete(final Long id) {
        documentRepository.deleteById(id);
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

package ca.fly.mtm.admin.rest;

import ca.fly.mtm.admin.model.DocumentDTO;
import ca.fly.mtm.admin.model.RequestResult;
import ca.fly.mtm.admin.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/documents", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Retrieves full list of ApplicantDTO objects sorted by ID. Optionally, the list can be filtered by
     * matching a document ID documentId.
     *
     * @param applicationId (Optional) a unique identifier for an application. If specified, the method
     *                      will return documents only for this application; otherwise, it returns
     *                      all documents.
     * @return a ResponseEntity containing a list of DocumentDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getDocuments(
            @RequestParam(value = "application-id", required = false) Long applicationId) {
        boolean filteredByApplicationId = applicationId != null;
        List<DocumentDTO> documents;

        if (filteredByApplicationId) {
            documents = documentService.getByApplicationId(applicationId);
            log.debug("Got {} documents for application id={}", documents.size(), applicationId);
        } else {
            documents = documentService.getAll();
            log.debug("Got {} documents", documents.size());
        }
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable @NotNull Long documentId) {
        DocumentDTO document = documentService.getById(documentId);
        log.debug("Got document with id={}", documentId);
        return ResponseEntity.ok(document);
    }

    @PostMapping
    public ResponseEntity<Long> createDocument(@RequestBody @Valid DocumentDTO documentDTO) {
        DocumentDTO document = documentService.create(documentDTO);
        log.debug("New document with id={} is created", document.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(document.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates the information of an existing document identified by the given ID.
     *
     * @param documentId  The ID of the document to be updated. This parameter must not be {@code null}.
     * @param documentDTO A DTO object holding the new information for the document. This object must be valid.
     * @return A {@code ResponseEntity} object containing a {@code RequestResult} object that holds the status
     *         and a message indicating the outcome of the operation. Returns a 200 OK status with a success message
     *         if the update is successful; returns a 404 Not Found status if a document with the given ID does not
     *         exist.
     */
    @PutMapping("/{documentId}")
    public ResponseEntity<RequestResult> updateDocument(@PathVariable @NotNull Long documentId,
                                                        @RequestBody @Valid DocumentDTO documentDTO) {
        Boolean documentExists = documentService.existsById(documentId);
        if (!documentExists) {
            log.error("Requested document with id={} does not exist", documentId);
            return ResponseEntity.notFound().build();
        }
        RequestResult result = new RequestResult();

        documentService.update(documentDTO);
        log.debug("Document with id={} is updated", documentId);

        result.setStatus("ok");
        result.setMsg("Successfully updated a document with id=" + documentId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<RequestResult> deleteDocument(@PathVariable @NotNull Long documentId) {
        RequestResult result = new RequestResult();

        documentService.delete(documentId);
        log.debug("Document with id={} is deleted", documentId);

        result.setStatus("ok");
        result.setMsg("Successfully deleted a document");

        return ResponseEntity.ok(result);
    }
}

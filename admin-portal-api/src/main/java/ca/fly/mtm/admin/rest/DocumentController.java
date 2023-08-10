package ca.fly.mtm.admin.rest;

import ca.fly.mtm.admin.model.DocumentDTO;
import ca.fly.mtm.admin.model.RequestResult;
import ca.fly.mtm.admin.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/document", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/all")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAll());
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable Long documentId) {
        return ResponseEntity.ok(documentService.getById(documentId));
    }

    @PostMapping("/new")
    public ResponseEntity<Long> createDocument(@RequestBody @Valid DocumentDTO documentDTO) {
        return new ResponseEntity<>(documentService.create(documentDTO), HttpStatus.CREATED);
    }

    @PostMapping("/update/{documentId}")
    public ResponseEntity<RequestResult> updateDocument(@PathVariable Long documentId,
                                                        @RequestBody @Valid DocumentDTO documentDTO) {
        RequestResult result = new RequestResult();

        documentService.update(documentId, documentDTO);

        result.setStatus("ok");
        result.setMsg("Successfully updated a document");

        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete/{documentId}")
    public ResponseEntity<RequestResult> deleteDocument(@PathVariable Long documentId) {
        RequestResult result = new RequestResult();

        documentService.delete(documentId);

        result.setStatus("ok");
        result.setMsg("Successfully deleted a document");

        return ResponseEntity.ok(result);
    }
}

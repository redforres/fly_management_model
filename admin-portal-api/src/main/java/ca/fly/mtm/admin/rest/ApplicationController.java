package ca.fly.mtm.admin.rest;

import ca.fly.mtm.admin.model.ApplicationDTO;
import ca.fly.mtm.admin.model.RequestResult;
import ca.fly.mtm.admin.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/applications", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>>
    getApplications(@PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(applicationService.getAll(pageable));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO>
    getApplicationById(@PathVariable Long applicationId) {
        return ResponseEntity.ok(applicationService.getById(applicationId));
    }

    @PostMapping
    public ResponseEntity<Long>
    createApplication(@RequestBody @Valid ApplicationDTO applicationDTO) {
        return new ResponseEntity<>(applicationService.create(applicationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<RequestResult>
    updateApplication(@PathVariable Long applicationId,
                      @RequestBody @Valid ApplicationDTO applicationDTO
    ) {
        RequestResult result = new RequestResult();

        applicationService.update(applicationId, applicationDTO);

        result.setStatus("ok");
        result.setMsg("Successfully updated an application");

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<RequestResult>
    deleteApplication(@PathVariable Long applicationId) {
        RequestResult result = new RequestResult();

        applicationService.delete(applicationId);

        result.setStatus("ok");
        result.setMsg("Successfully deleted an application");

        return ResponseEntity.ok(result);
    }
}

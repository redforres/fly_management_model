package ca.fly.mtm.admin.rest;

import ca.fly.mtm.admin.model.ApplicationDTO;
import ca.fly.mtm.admin.model.RequestResult;
import ca.fly.mtm.admin.service.ApplicationService;
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
@RequestMapping(value = "/api/applications", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationController implements ServiceExceptionHandler {

    private final ApplicationService applicationService;

    @Autowired
    ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * Retrieves full list of ApplicantDTO objects sorted by ID. Optionally, the list can be filtered by
     * matching an applicant ID applicantId.
     *
     * @param applicantId (Optional) a unique identifier for an applicant. If specified, the method
     *                    will return applications only for this applicant; otherwise, it returns
     *                    all applications.
     * @return a ResponseEntity containing a list of ApplicationDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getApplications(
            @RequestParam(value = "applicant-id", required = false) Long applicantId) {
        boolean filteredByApplicantId = applicantId != null;
        List<ApplicationDTO> applications;

        if (filteredByApplicantId) {
            applications = applicationService.getByApplicantId(applicantId);
            log.debug("Got {} application(s) for applicant id={}", applications.size(), applicantId);
        } else {
            applications = applicationService.getAll();
            log.debug("Got {} application(s)", applications.size());
        }
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable @NotNull Long applicationId) {
        ApplicationDTO application = applicationService.getById(applicationId);
        log.debug("Got application with id={}", applicationId);
        return ResponseEntity.ok(application);
    }

    @PostMapping
    public ResponseEntity<Long> createApplication(@RequestBody @Valid ApplicationDTO applicationDTO) {
        ApplicationDTO application = applicationService.create(applicationDTO);
        log.debug("New application with id={} is created", application.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(application.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates the information of an existing application identified by the given ID.
     *
     * @param applicationId  The ID of the application to be updated. This parameter must not be {@code null}.
     * @param applicationDTO A DTO object holding the new information for the application. This object must be valid.
     * @return A {@code ResponseEntity} object containing a {@code RequestResult} object that holds the status
     *         and a message indicating the outcome of the operation. Returns a 200 OK status with a success message
     *         if the update is successful; returns a 404 Not Found status if an application with the given ID does not
     *         exist.
     */
    @PutMapping("/{applicationId}")
    public ResponseEntity<RequestResult> updateApplication(@PathVariable @NotNull Long applicationId,
                                                           @RequestBody @Valid ApplicationDTO applicationDTO) {
        Boolean applicationExists = applicationService.existsById(applicationId);
        if (!applicationExists) {
            log.error("Requested application with id={} does not exist", applicationId);
            return ResponseEntity.notFound().build();
        }
        RequestResult result = new RequestResult();

        applicationService.update(applicationDTO);
        log.debug("Application with id={} is updated", applicationId);

        result.setStatus("ok");
        result.setMsg("Successfully updated an application with id=" + applicationId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<RequestResult> deleteApplication(@PathVariable @NotNull Long applicationId) {
        RequestResult result = new RequestResult();

        applicationService.delete(applicationId);
        log.debug("Application with id={} is deleted", applicationId);

        result.setStatus("ok");
        result.setMsg("Successfully deleted an application");

        return ResponseEntity.ok(result);
    }
}

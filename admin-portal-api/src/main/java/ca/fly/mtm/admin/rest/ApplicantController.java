package ca.fly.mtm.admin.rest;

import ca.fly.mtm.admin.model.ApplicantDTO;
import ca.fly.mtm.admin.model.RequestResult;
import ca.fly.mtm.admin.service.ApplicantService;
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
@RequestMapping(value = "/api/applicants", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicantController implements ServiceExceptionHandler {

    private final ApplicantService applicantService;

    @Autowired
    ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicantDTO>> getApplicants() {
        List<ApplicantDTO> applicants = applicantService.getAll();
        log.debug("Got {} applicants", applicants.size());
        return ResponseEntity.ok(applicants);
    }

    @GetMapping("/{applicantId}")
    public ResponseEntity<ApplicantDTO> getApplicantById(@PathVariable @NotNull Long applicantId) {
        ApplicantDTO applicant = applicantService.getById(applicantId);
        log.debug("Got applicant with id={}", applicantId);
        return ResponseEntity.ok(applicant);
    }

    @PostMapping
    public ResponseEntity<Void> createApplicant(@RequestBody @Valid ApplicantDTO applicantDTO) {
        ApplicantDTO applicant = applicantService.create(applicantDTO);
        log.debug("New applicant with id={} is created", applicant.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(applicant.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates the information of an existing applicant identified by the given ID.
     *
     * @param applicantId  The ID of the applicant to be updated. This parameter must not be {@code null}.
     * @param applicantDTO A DTO object holding the new information for the applicant. This object must be valid.
     * @return A {@code ResponseEntity} object containing a {@code RequestResult} object that holds the status
     *         and a message indicating the outcome of the operation. Returns a 200 OK status with a success message
     *         if the update is successful; returns a 404 Not Found status if an applicant with the given ID does not
     *         exist.
     */
    @PutMapping("/{applicantId}")
    public ResponseEntity<RequestResult> updateApplicant(@PathVariable @NotNull Long applicantId,
                                                         @RequestBody @Valid ApplicantDTO applicantDTO) {
        Boolean applicantExists = applicantService.existsById(applicantId);
        if (!applicantExists) {
            log.error("Requested applicant with id={} does not exist", applicantId);
            return ResponseEntity.notFound().build();
        }
        RequestResult result = new RequestResult();

        applicantService.update(applicantDTO);
        log.debug("Applicant with id={} is updated", applicantId);

        result.setStatus("ok");
        result.setMsg("Successfully updated an applicant with id=" + applicantId);
        return ResponseEntity.ok(result);
    }

    /**
     * Updates the skill set of an existing applicant identified by the given ID.
     *
     * @param applicantId  The ID of the applicant whose skill set is to be updated. This parameter must not be
     *                     {@code null}.
     * @param skillIds     A list of skill IDs to be associated with the applicant. It can be an empty list to clear
     *                     the current skills associated with the applicant.
     * @return A {@code ResponseEntity} object containing a {@code RequestResult} object that holds the status
     *         and a message indicating the outcome of the operation. Returns a 200 OK status with a success message
     *         indicating the number of skills set for the applicant.
     */
    @PutMapping("/{applicantId}/skills")
    public ResponseEntity<RequestResult> updateApplicantSkills(@PathVariable @NotNull Long applicantId,
                                                               @RequestBody List<Long> skillIds) {
        RequestResult result = new RequestResult();

        applicantService.updateSkills(applicantId, skillIds);
        log.debug("Set {} skills for applicant with id={}", skillIds.size(), applicantId);

        result.setStatus("ok");
        result.setMsg("Successfully set " + skillIds.size() + " skills for applicant id=" + applicantId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{applicantId}")
    public ResponseEntity<RequestResult> deleteApplicant(@PathVariable Long applicantId) {
        RequestResult result = new RequestResult();

        applicantService.delete(applicantId);
        log.debug("Applicant with id={} is deleted", applicantId);

        result.setStatus("ok");
        result.setMsg("Successfully deleted an applicant");
        return ResponseEntity.ok(result);
    }
}

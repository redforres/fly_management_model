package ca.fly.mtm.admin.rest;

import ca.fly.mtm.admin.model.ApplicantDTO;
import ca.fly.mtm.admin.model.RequestResult;
import ca.fly.mtm.admin.service.ApplicantService;
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
@RequestMapping(value = "/api/applicants", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

    @GetMapping
    public ResponseEntity<List<ApplicantDTO>>
    getAllApplicants(@PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(applicantService.getAll(pageable));
    }

    @GetMapping("/{applicantId}")
    public ResponseEntity<ApplicantDTO>
    getApplicantById(@PathVariable Long applicantId) {
        return ResponseEntity.ok(applicantService.getById(applicantId));
    }

    @PostMapping
    public ResponseEntity<Long>
    createApplicant(@RequestBody @Valid ApplicantDTO applicantDTO) {
        return new ResponseEntity<>(applicantService.create(applicantDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{applicantId}")
    public ResponseEntity<RequestResult>
    updateApplicant(@PathVariable Long applicantId,
                    @RequestBody @Valid ApplicantDTO applicantDTO
    ) {
        RequestResult result = new RequestResult();

        applicantService.update(applicantId, applicantDTO);

        result.setStatus("ok");
        result.setMsg("Successfully updated an applicant");

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{applicantId}")
    public ResponseEntity<RequestResult>
    deleteApplicant(@PathVariable Long applicantId) {
        RequestResult result = new RequestResult();

        applicantService.delete(applicantId);

        result.setStatus("ok");
        result.setMsg("Successfully deleted an applicant");

        return ResponseEntity.ok(result);
    }
}

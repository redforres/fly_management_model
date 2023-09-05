package ca.fly.mtm.admin.rest;


import java.util.List;
import javax.validation.Valid;

import ca.fly.mtm.admin.model.RequestResult;
import ca.fly.mtm.admin.service.SkillService;
import ca.fly.mtm.admin.model.SkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/skills", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<List<SkillDTO>>
    getSkills(
            @PageableDefault(sort = "id") Pageable pageable,
            @RequestParam(value = "applicant-id", required = false) Long applicantId
    ) {
        if (applicantId != null) {
            return ResponseEntity.ok(skillService.getByApplicantId(applicantId));
        }
        return ResponseEntity.ok(skillService.getAll(pageable));
    }

    @GetMapping("/{skillId}")
    public ResponseEntity<SkillDTO>
    getSkillById(@PathVariable Long skillId) {
        return ResponseEntity.ok(skillService.getById(skillId));
    }

    @PostMapping
    public ResponseEntity<Long>
    createSkill(@RequestBody @Valid SkillDTO skillDTO) {
        return new ResponseEntity<>(skillService.create(skillDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<RequestResult>
    updateSkill(@PathVariable Long skillId,
                @RequestBody @Valid SkillDTO skillDTO
    ) {
        RequestResult result = new RequestResult();

        skillService.update(skillId, skillDTO);

        result.setStatus("ok");
        result.setMsg("Successfully updated a skill");

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<RequestResult>
    deleteSkill(@PathVariable Long skillId) {
        RequestResult result = new RequestResult();

        skillService.delete(skillId);

        result.setStatus("ok");
        result.setMsg("Successfully deleted");

        return ResponseEntity.ok(result);
    }

}

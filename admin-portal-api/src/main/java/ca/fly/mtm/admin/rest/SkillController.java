package ca.fly.mtm.admin.rest;


import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import ca.fly.mtm.admin.model.RequestResult;
import ca.fly.mtm.admin.service.SkillService;
import ca.fly.mtm.admin.model.SkillDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/skills", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkillController {

    private final SkillService skillService;

    @Autowired
    SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    /**
     * Retrieves full list of SkillDTO objects sorted by ID. Optionally, the list can be filtered by
     * matching an applicant ID applicantId.
     *
     * @param applicantId (Optional) a unique identifier for an applicant. If specified, the method
     *                    will return skills only for this applicant; otherwise, it returns
     *                    all applications.
     * @return a ResponseEntity containing a list of SkillDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<SkillDTO>> getSkills(
            @RequestParam(value = "applicant-id", required = false) Long applicantId) {
        boolean filteredByApplicantId = applicantId != null;
        List<SkillDTO> skills;

        if (filteredByApplicantId) {
            skills = skillService.getByApplicantId(applicantId);
            log.debug("Got {} skills for applicant id={}", skills.size(), applicantId);
        } else {
            skills = skillService.getAll();
            log.debug("Got {} skills", skills.size());
        }
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{skillId}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable @NotNull Long skillId) {
        SkillDTO skill = skillService.getById(skillId);
        log.debug("Got skill with id={}", skillId);
        return ResponseEntity.ok(skill);
    }

    @PostMapping
    public ResponseEntity<Long> createSkill(@RequestBody @Valid SkillDTO skillDTO) {
        SkillDTO skill = skillService.create(skillDTO);
        log.debug("New skill with id={} is created", skill.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(skill.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates the information of an existing skill identified by the given ID.
     *
     * @param skillId  The ID of the skill to be updated. This parameter must not be {@code null}.
     * @param skillDTO A DTO object holding the new information for the skill. This object must be valid.
     * @return A {@code ResponseEntity} object containing a {@code RequestResult} object that holds the status
     *         and a message indicating the outcome of the operation. Returns a 200 OK status with a success message
     *         if the update is successful; returns a 404 Not Found status if a skill with the given ID does not
     *         exist.
     */
    @PutMapping("/{skillId}")
    public ResponseEntity<RequestResult> updateSkill(@PathVariable @NotNull Long skillId,
                                                     @RequestBody @Valid SkillDTO skillDTO) {
        Boolean skillExists = skillService.existsById(skillId);
        if (!skillExists) {
            log.error("Requested skill with id={} does not exist", skillId);
            return ResponseEntity.notFound().build();
        }
        RequestResult result = new RequestResult();

        skillService.update(skillDTO);
        log.debug("Skill with id={} is updated", skillId);

        result.setStatus("ok");
        result.setMsg("Successfully updated a skill with id=" + skillId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<RequestResult> deleteSkill(@PathVariable @NotNull Long skillId) {
        RequestResult result = new RequestResult();

        skillService.delete(skillId);
        log.debug("Skill with id={} is deleted", skillId);

        result.setStatus("ok");
        result.setMsg("Successfully deleted");

        return ResponseEntity.ok(result);
    }

}

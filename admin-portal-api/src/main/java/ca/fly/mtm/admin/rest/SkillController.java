package ca.fly.mtm.admin.rest;


import java.util.List;
import javax.validation.Valid;

import ca.fly.mtm.admin.model.ApplicationResult;
import ca.fly.mtm.admin.service.SkillService;
import ca.fly.mtm.admin.model.SkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/skill", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/search")
    public ResponseEntity<List<SkillDTO>> getAllSkillOffices() {
        return ResponseEntity.ok(skillService.findAll());
    }


    @PostMapping("/new")
    public ResponseEntity<Long> createSkillOffice(
            @RequestBody @Valid SkillDTO skillDTO) {
        return new ResponseEntity<>(skillService.create(skillDTO), HttpStatus.CREATED);
    }

    @PostMapping("/update/{skillId}")
    public ResponseEntity<ApplicationResult> updateSkillOffice(@PathVariable Long skillId,
                                                               @RequestBody @Valid SkillDTO skillDTO) {

        ApplicationResult result = new ApplicationResult();

        skillService.update(skillId, skillDTO);


        result.setStatus("ok");
        result.setMsg("Successfully updated");

        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete/{skillId}")
    public ResponseEntity<ApplicationResult> deleteSkillOffice(@PathVariable Long skillId) {

        ApplicationResult result = new ApplicationResult();

        skillService.delete(skillId);

        result.setStatus("ok");
        result.setMsg("Successfully deleted");

        return ResponseEntity.ok(result);
    }

}

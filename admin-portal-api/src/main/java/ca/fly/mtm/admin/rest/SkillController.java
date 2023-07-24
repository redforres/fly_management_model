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
@RequestMapping(value = "/api/municipality", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkillController {

    @Autowired
    private SkillService officeService;

    @GetMapping("/search")
    public ResponseEntity<List<SkillDTO>> getAllMunicipalityOffices() {
        return ResponseEntity.ok(officeService.findAll());
    }


    @PostMapping("/new")
    public ResponseEntity<Long> createMunicipalityOffice(
            @RequestBody @Valid SkillDTO municipalityDTO) {
        return new ResponseEntity<>(officeService.create(municipalityDTO), HttpStatus.CREATED);
    }

    @PostMapping("/update/{municipalityId}")
    public ResponseEntity<ApplicationResult> updateMunicipalityOffice(@PathVariable Long municipalityId,
                                                                      @RequestBody @Valid SkillDTO municipalityDTO) {

        ApplicationResult result = new ApplicationResult();

        officeService.update(municipalityId, municipalityDTO);


        result.setStatus("ok");
        result.setMsg("Successfuly updated");

        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete/{municipalityId}")
    public ResponseEntity<ApplicationResult> deleteMunicipalityOffice(@PathVariable Long municipalityId) {

        ApplicationResult result = new ApplicationResult();

        officeService.delete(municipalityId);

        result.setStatus("ok");
        result.setMsg("Successfuly deleted");

        return ResponseEntity.ok(result);
    }

}

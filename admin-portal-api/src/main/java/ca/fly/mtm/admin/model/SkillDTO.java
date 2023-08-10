package ca.fly.mtm.admin.model;

import javax.validation.constraints.Size;

import ca.fly.mtm.admin.entity.ApplicantSkill;
import lombok.*;

import java.util.List;

@Builder(builderClassName = "Builder", toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillDTO {

    private Long id;

    @Size(max = 20)
    private String category;

    @Size(max = 20)
    private String name;

    private List<ApplicantSkill> applicantSkills;
}

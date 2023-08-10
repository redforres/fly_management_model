package ca.fly.mtm.admin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder(builderClassName = "Builder", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"F_Applicant_Skill\"")
@Getter
@Setter
public class ApplicantSkill {
    @EmbeddedId
    private ApplicantSkillId id;

    @ManyToOne
    @MapsId("applicantId")
    @JoinColumn(name = "applicant_id")
    @JsonBackReference
    private Applicant applicant_AS;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    @JsonBackReference
    private Skill skill_AS;
}

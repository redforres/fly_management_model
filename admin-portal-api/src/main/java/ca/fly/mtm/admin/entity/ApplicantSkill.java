package ca.fly.mtm.admin.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder(builderClassName = "Builder", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "F_Applicant_Skill")
@Getter
@Setter
public class ApplicantSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "applicant_id")
    private Long applicantId;

    @Column(name = "skill_id")
    private Long skillId;
}

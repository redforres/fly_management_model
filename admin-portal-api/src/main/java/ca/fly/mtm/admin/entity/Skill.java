package ca.fly.mtm.admin.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder(builderClassName = "Builder", toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"F_Skill\"")
@Getter
@Setter
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "skill_AS", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ApplicantSkill> applicantSkills;
}

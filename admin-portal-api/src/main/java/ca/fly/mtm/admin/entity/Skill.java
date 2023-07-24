package ca.fly.mtm.admin.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Builder(builderClassName = "Builder", toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "F_SKILL")
@Getter
@Setter

public class Skill {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long skillId;

    @Column (name="CATEGORY")
    private String category;

    @Column (name="NAME")
    private String name;
}

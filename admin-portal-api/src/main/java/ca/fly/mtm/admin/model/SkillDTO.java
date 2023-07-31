package ca.fly.mtm.admin.model;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Builder(builderClassName = "Builder", toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillDTO {

    private Long id;

    @Size(max = 255)
    private String category;

    @Size(max = 255)
    private String name;

//    @JsonFormat(pattern = "MMM dd, yyyy")
//    private LocalDate createdDate;
}

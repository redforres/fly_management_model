package ca.fly.mtm.admin.model;

import ca.fly.mtm.admin.entity.Applicant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder(builderClassName = "Builder", toBuilder = true)
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ApplicationDTO {
    private Long id;

    private Applicant applicant;

    @Size(max = 40)
    private String role;

    @Size(max = 20)
    private String status;

    @Size(max = 20)
    private String createdBy;

    @Size(max = 20)
    private String modifiedBy;

    @JsonFormat(pattern = "MMM dd, yyyy")
    private LocalDate createdAt;

    @JsonFormat(pattern = "MMM dd, yyyy")
    private LocalDate modifiedAt;
}

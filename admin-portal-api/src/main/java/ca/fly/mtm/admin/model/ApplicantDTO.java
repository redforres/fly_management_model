package ca.fly.mtm.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder(builderClassName = "Builder", toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicantDTO {
    private Long id;

    @Size(max = 20)
    private String firstName;

    @Size(max = 20)
    private String middleName;

    @Size(max = 20)
    private String lastName;

    @Size(max = 50)
    private String email;

    @Size(max = 20)
    private String phone;

    @Size(max = 20)
    private String wechat;

    @Size(max = 20)
    private String linkedIn;

    @Size(max = 100)
    private String addressStreet;

    @Size(max = 10)
    private String addressSuiteNumber;

    @Size(max = 50)
    private String addressCity;

    @Size(max = 20)
    private String addressPostalCode;

    @Size(max = 20)
    private String addressProvince;

    @Size(max = 20)
    private String addressCountry;

    @Size(max = 20)
    private String createdBy;

    @Size(max = 20)
    private String modifiedBy;

    @JsonFormat(pattern = "MMM dd, yyyy")
    private LocalDate createdAt;

    @JsonFormat(pattern = "MMM dd, yyyy")
    private LocalDate modifiedAt;
}

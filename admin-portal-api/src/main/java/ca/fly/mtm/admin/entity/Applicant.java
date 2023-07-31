package ca.fly.mtm.admin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder(builderClassName = "Builder", toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "F_Applicant")
@Getter
@Setter
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "WeChat")
    private String wechat;

    @Column(name = "LinkedIn")
    private String linkedIn;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_suite_number")
    private String addressSuiteNumber;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_postal_code")
    private String addressPostalCode;

    @Column(name = "address_province")
    private String addressProvince;

    @Column(name = "address_country")
    private String addressCountry;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "modified_at")
    private Date modifiedAt;
}

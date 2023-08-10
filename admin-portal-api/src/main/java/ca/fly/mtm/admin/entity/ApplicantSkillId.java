package ca.fly.mtm.admin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter
public class ApplicantSkillId implements Serializable {
    private Long applicantId;
    private Long skillId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantSkillId that = (ApplicantSkillId) o;
        return Objects.equals(applicantId, that.applicantId) && Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicantId, skillId);
    }
}

package ca.fly.mtm.admin.service;

import ca.fly.mtm.admin.entity.Applicant;
import ca.fly.mtm.admin.entity.ApplicantSkill;
import ca.fly.mtm.admin.entity.ApplicantSkillId;
import ca.fly.mtm.admin.entity.Skill;
import ca.fly.mtm.admin.model.ApplicantDTO;
import ca.fly.mtm.admin.repository.ApplicantRepository;
import ca.fly.mtm.admin.repository.ApplicantSkillRepository;
import ca.fly.mtm.admin.repository.SkillRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final SkillRepository skillRepository;
    private final ApplicantSkillRepository applicantSkillRepository;

    @Autowired
    public ApplicantService(
            ApplicantRepository applicantRepository,
            SkillRepository skillRepository,
            ApplicantSkillRepository applicantSkillRepository) {

        this.applicantRepository = applicantRepository;
        this.skillRepository = skillRepository;
        this.applicantSkillRepository = applicantSkillRepository;
    }

    public Boolean existsById(final Long id) {
        return applicantRepository.existsById(id);
    }

    public List<ApplicantDTO> getAll() {
        return applicantRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .sorted(Comparator.comparing(ApplicantDTO::getId)) // sort by ID
                .collect(Collectors.toList());
    }

    public ApplicantDTO getById(final Long id) {
        return applicantRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find applicant with id=" + id));
    }

    public ApplicantDTO create(final ApplicantDTO applicantDTO) {
        Applicant applicant = new Applicant();
        mapToEntity(applicantDTO, applicant);
        return mapToDTO(applicantRepository.save(applicant));
    }

    public void update(final ApplicantDTO applicantDTO) {
        final Applicant applicant = new Applicant();
        mapToEntity(applicantDTO, applicant);
        applicantRepository.save(applicant);
    }

    public void updateSkills(final Long applicantId, final List<Long> skillIds) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find applicant with id=" + applicantId));
        List<Skill> skills = skillRepository.findAllById(skillIds);

        if (skills.size() != skillIds.size()) {
            skillIds.removeAll(skills.stream().map(Skill::getId).collect(Collectors.toList()));
            throw new EntityNotFoundException("Cannot find skill(s) with ids=" + skillIds);
        }
        applicantSkillRepository.deleteAllByIdApplicantId(applicantId);
        Function<Skill, ApplicantSkill> skillToApplicantSkill = skill -> new ApplicantSkill(
                new ApplicantSkillId(applicantId, skill.getId()),
                applicant,
                skill
        );
        List<ApplicantSkill> applicantSkills = skills.stream()
                .map(skillToApplicantSkill)
                .collect(Collectors.toList());
        applicantSkillRepository.saveAll(applicantSkills);
    }

    public void delete(final Long applicantId) {
        try {
            applicantRepository.deleteById(applicantId);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Cannot find applicant with id=" + applicantId);
        }
    }

    private ApplicantDTO mapToDTO(Applicant applicant) {
        ApplicantDTO.Builder builder = ApplicantDTO.builder()
                .id(applicant.getId())
                .firstName(applicant.getFirstName())
                .middleName(applicant.getMiddleName())
                .lastName(applicant.getLastName())
                .email(applicant.getEmail())
                .phone(applicant.getPhone())
                .wechat(applicant.getWechat())
                .linkedIn(applicant.getLinkedIn())
                .addressStreet(applicant.getAddressStreet())
                .addressSuiteNumber(applicant.getAddressSuiteNumber())
                .addressCity(applicant.getAddressCity())
                .addressPostalCode(applicant.getAddressPostalCode())
                .addressProvince(applicant.getAddressProvince())
                .addressCountry(applicant.getAddressCountry())
                .createdBy(applicant.getCreatedBy())
                .modifiedBy(applicant.getModifiedBy())
                .createdAt(applicant.getCreatedAt())
                .modifiedAt(applicant.getModifiedAt());

        return builder.build();
    }

    @SuppressWarnings("UnusedReturnValue")
    private Applicant mapToEntity(ApplicantDTO applicantDTO, Applicant applicant) {
        if (applicantDTO.getFirstName() != null) {
            applicant.setFirstName(applicantDTO.getFirstName());
        }

        if (applicantDTO.getMiddleName() != null) {
            applicant.setMiddleName(applicantDTO.getMiddleName());
        }

        if (applicantDTO.getLastName() != null) {
            applicant.setLastName(applicantDTO.getLastName());
        }

        if (applicantDTO.getEmail() != null) {
            applicant.setEmail(applicantDTO.getEmail());
        }

        if (applicantDTO.getPhone() != null) {
            applicant.setPhone(applicantDTO.getPhone());
        }

        if (applicantDTO.getWechat() != null) {
            applicant.setWechat(applicantDTO.getWechat());
        }

        if (applicantDTO.getLinkedIn() != null) {
            applicant.setLinkedIn(applicantDTO.getLinkedIn());
        }

        if (applicantDTO.getAddressStreet() != null) {
            applicant.setAddressStreet(applicantDTO.getAddressStreet());
        }

        if (applicantDTO.getAddressSuiteNumber() != null) {
            applicant.setAddressSuiteNumber(applicantDTO.getAddressSuiteNumber());
        }

        if (applicantDTO.getAddressCity() != null) {
            applicant.setAddressCity(applicantDTO.getAddressCity());
        }

        if (applicantDTO.getAddressPostalCode() != null) {
            applicant.setAddressPostalCode(applicantDTO.getAddressPostalCode());
        }

        if (applicantDTO.getAddressProvince() != null) {
            applicant.setAddressProvince(applicantDTO.getAddressProvince());
        }

        if (applicantDTO.getAddressCountry() != null) {
            applicant.setAddressCountry(applicantDTO.getAddressCountry());
        }

        if (applicantDTO.getCreatedBy() != null) {
            applicant.setCreatedBy(applicantDTO.getCreatedBy());
        }

        if (applicantDTO.getModifiedBy() != null) {
            applicant.setModifiedBy(applicantDTO.getModifiedBy());
        }

        if (applicantDTO.getCreatedAt() != null) {
            applicant.setCreatedAt(applicantDTO.getCreatedAt());
        }

        if (applicantDTO.getModifiedAt() != null) {
            applicant.setModifiedAt(applicantDTO.getModifiedAt());
        }

        return applicant;
    }
}

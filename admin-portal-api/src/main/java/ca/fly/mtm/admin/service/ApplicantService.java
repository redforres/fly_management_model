package ca.fly.mtm.admin.service;

import ca.fly.mtm.admin.entity.Applicant;
import ca.fly.mtm.admin.model.ApplicantDTO;
import ca.fly.mtm.admin.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public List<ApplicantDTO> getAll() {
        return applicantRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

//    public List<> getApplicationsByApplicantId(final Long id) {}

    public ApplicantDTO getById(final Long id) {
        return applicantRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ApplicantDTO applicantDTO) {
        Applicant applicant = new Applicant();
        mapToEntity(applicantDTO, applicant);
        return applicantRepository.save(applicant).getId();
    }

    public void update(final Long id, final ApplicantDTO applicantDTO) {
        final Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(applicantDTO, applicant);
        applicantRepository.save(applicant);
    }

    public void delete(final Long applicantId) {
        applicantRepository.deleteById(applicantId);
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
                .modifiedAt(applicant.getModifiedAt())
                .applications(applicant.getApplications())
                .applicantSkills(applicant.getApplicantSkills());

        return builder.build();
    }

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

        if (applicantDTO.getApplications() != null) {
            applicant.setApplications(applicantDTO.getApplications());
        }

        if (applicantDTO.getApplicantSkills() != null) {
            applicant.setApplicantSkills(applicantDTO.getApplicantSkills());
        }

        return applicant;
    }
}

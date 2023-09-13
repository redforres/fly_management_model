package ca.fly.mtm.admin.service;

import ca.fly.mtm.admin.entity.Application;
import ca.fly.mtm.admin.model.ApplicationDTO;
import ca.fly.mtm.admin.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    ApplicationService(ApplicationRepository applicantRepository) {
        this.applicationRepository = applicantRepository;
    }

    public Boolean existsById(final Long id) {
        return applicationRepository.existsById(id);
    }

    public List<ApplicationDTO> getAll() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .sorted(Comparator.comparing(ApplicationDTO::getId)) // sort by ID
                .collect(Collectors.toList());
    }

    public ApplicationDTO getById(final Long id) {
        return applicationRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find application with id=" + id));
    }

    public List<ApplicationDTO> getByApplicantId(final Long applicantId) {
        return applicationRepository.findByApplicant_Id(applicantId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ApplicationDTO create(final ApplicationDTO applicationDTO) {
        Application application = new Application();
        mapToEntity(applicationDTO, application);
        return mapToDTO(applicationRepository.save(application));
    }

    public void update(final ApplicationDTO applicationDTO) {
        final Application application = new Application();
        mapToEntity(applicationDTO, application);
        applicationRepository.save(application);
    }

    public void delete(final Long id) {
        try {
            applicationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Cannot find application with id=" + id);
        }
    }

    private ApplicationDTO mapToDTO(Application application) {
        ApplicationDTO.Builder builder = ApplicationDTO.builder()
                .id(application.getId())
                .applicant(application.getApplicant())
                .role(application.getRole())
                .status(application.getStatus())
                .createdBy(application.getCreatedBy())
                .modifiedBy(application.getModifiedBy())
                .createdAt(application.getCreatedAt())
                .modifiedAt(application.getModifiedAt());

        return builder.build();
    }

    @SuppressWarnings("UnusedReturnValue")
    private Application mapToEntity(ApplicationDTO applicationDTO, Application application) {
        if (applicationDTO.getId() != null) {
            application.setId(application.getId());
        }

        if (applicationDTO.getApplicant() != null) {
            application.setApplicant(application.getApplicant());
        }

        if (applicationDTO.getRole() != null) {
            application.setRole(application.getRole());
        }

        if (applicationDTO.getStatus() != null) {
            application.setStatus(application.getStatus());
        }

        if (applicationDTO.getCreatedBy() != null) {
            application.setCreatedBy(application.getCreatedBy());
        }

        if (applicationDTO.getModifiedBy() != null) {
            application.setModifiedBy(application.getModifiedBy());
        }

        if (applicationDTO.getCreatedAt() != null) {
            application.setCreatedAt(application.getCreatedAt());
        }

        if (applicationDTO.getModifiedAt() != null) {
            application.setModifiedAt(application.getModifiedAt());
        }

        return application;
    }
}

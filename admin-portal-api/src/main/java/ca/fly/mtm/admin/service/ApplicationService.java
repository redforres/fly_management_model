package ca.fly.mtm.admin.service;

import ca.fly.mtm.admin.entity.Application;
import ca.fly.mtm.admin.model.ApplicationDTO;
import ca.fly.mtm.admin.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public List<ApplicationDTO> getAll() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ApplicationDTO getById(final Long id) {
        return applicationRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ApplicationDTO applicationDTO) {
        Application application = new Application();
        mapToEntity(applicationDTO, application);
        return applicationRepository.save(application).getId();
    }

    public void update(final Long id, final ApplicationDTO applicationDTO) {
        final Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(applicationDTO, application);
        applicationRepository.save(application);
    }

    public void delete(final Long applicationId) {
        applicationRepository.deleteById(applicationId);
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
                .modifiedAt(application.getModifiedAt())
                .documents(application.getDocuments());

        return builder.build();
    }

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

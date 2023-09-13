package ca.fly.mtm.admin.service;


import ca.fly.mtm.admin.entity.ApplicantSkill;
import ca.fly.mtm.admin.entity.Skill;
import ca.fly.mtm.admin.model.SkillDTO;
import ca.fly.mtm.admin.repository.ApplicantSkillRepository;
import ca.fly.mtm.admin.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final ApplicantSkillRepository applicantSkillRepository;

    @Autowired
    SkillService(SkillRepository skillRepository, ApplicantSkillRepository applicantSkillRepository) {
        this.skillRepository = skillRepository;
        this.applicantSkillRepository = applicantSkillRepository;
    }

    public Boolean existsById(final Long id) {
        return skillRepository.existsById(id);
    }

    public List<SkillDTO> getAll() {
        return skillRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .sorted(Comparator.comparing(SkillDTO::getId)) // sort by ID
                .collect(Collectors.toList());
    }

    public SkillDTO getById(final Long id) {
        return skillRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find skill with id=" + id));
    }

    public List<SkillDTO> getByApplicantId(final Long applicantId) {
        List<ApplicantSkill> applicantSkills = applicantSkillRepository.findByIdApplicantId(applicantId);
        return applicantSkills.stream()
                .map(ApplicantSkill::getSkill_AS)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public SkillDTO create(final SkillDTO skillDTO) {
        Skill skill = new Skill();
        mapToEntity(skillDTO, skill);
        return mapToDTO(skillRepository.save(skill));
    }

    public void update(final SkillDTO skillDTO) {
        final Skill skill = new Skill();
        mapToEntity(skillDTO, skill);
        skillRepository.save(skill);
    }

    public void delete(final Long id) {
        try {
            skillRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Cannot find skill with id=" + id);
        }
    }

    private SkillDTO mapToDTO(Skill skill) {

        SkillDTO.Builder builder = SkillDTO.builder()
                .id(skill.getId())
                .category(skill.getCategory())
                .name(skill.getName());

        return builder.build();
    }

    @SuppressWarnings("UnusedReturnValue")
    private Skill mapToEntity(SkillDTO skillDTO, Skill skill) {
        if (skillDTO.getCategory() != null) {
            skill.setCategory(skillDTO.getCategory());
        }

        if (skillDTO.getName() != null) {
            skill.setName(skillDTO.getName());
        }

        return skill;
    }
}

package ca.fly.mtm.admin.service;


import ca.fly.mtm.admin.entity.Skill;
import ca.fly.mtm.admin.model.SkillDTO;
import ca.fly.mtm.admin.repository.SkillRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;


    public List<SkillDTO> getAll() {
        return skillRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public SkillDTO getById(final Long id) {
        return skillRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SkillDTO skillDTO) {
        Skill office = new Skill();
        mapToEntity(skillDTO, office);
        return skillRepository.save(office).getId();
    }

    public void update(final Long id, final SkillDTO skillDTO) {
        final Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(skillDTO, skill);
        skillRepository.save(skill);
    }

    public void delete(final Long officeId) {
        skillRepository.deleteById(officeId);
    }

    private SkillDTO mapToDTO(Skill skill) {

        SkillDTO.Builder builder = SkillDTO.builder()
                .id(skill.getId())
                .category(skill.getCategory())
                .name(skill.getName())
                .applicantSkills(skill.getApplicantSkills());

        return builder.build();
    }

    private Skill mapToEntity(SkillDTO skillDTO, Skill skill) {

        if (!StringUtils.isEmpty(skillDTO.getCategory())) {
            skill.setCategory(skillDTO.getCategory());
        }

        if (!StringUtils.isEmpty(skillDTO.getName())) {
            skill.setName(skillDTO.getName());
        }

        if (!skillDTO.getApplicantSkills().isEmpty()) {
            skill.setApplicantSkills(skillDTO.getApplicantSkills());
        }

        return skill;
    }
}

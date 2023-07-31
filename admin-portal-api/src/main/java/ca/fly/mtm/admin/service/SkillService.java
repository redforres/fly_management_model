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


    public List<SkillDTO> findAll() {
        return skillRepository.findAll()
                .stream()
                .map(skill -> mapToDTO(skill))
                .collect(Collectors.toList());
    }

    public SkillDTO get(final Long skillId) {
        return skillRepository.findById(skillId)
                .map(office -> mapToDTO(office))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SkillDTO skillDTO) {
        Skill office = new Skill();
        office = mapToEntity(skillDTO, office);
        return skillRepository.save(office).getId();
    }

    public void update(final Long skilld, final SkillDTO skillDTO) {
        final Skill skill = skillRepository.findById(skilld)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        skillRepository.save(mapToEntity(skillDTO, skill));
    }

    public void delete(final Long officeId) {
        skillRepository.deleteById(officeId);
    }

    private SkillDTO mapToDTO(Skill skill) {

        SkillDTO.Builder builder = SkillDTO.builder()
                .id(skill.getId())
                .category(skill.getCategory())
                .name(skill.getName());

        return builder.build();
    }

    private Skill mapToEntity(SkillDTO skillDTO, Skill skill) {

        if (!StringUtils.isEmpty(skillDTO.getCategory())) {
            skill.setCategory(skillDTO.getCategory());
        }

        if (!StringUtils.isEmpty(skillDTO.getName())) {
            skill.setName(skillDTO.getName());
        }

        return skill;
    }
}

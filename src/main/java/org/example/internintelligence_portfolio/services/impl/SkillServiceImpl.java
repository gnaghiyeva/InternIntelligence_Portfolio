package org.example.internintelligence_portfolio.services.impl;

import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillCreateDto;
import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillDto;
import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillUpdateDto;
import org.example.internintelligence_portfolio.models.Project;
import org.example.internintelligence_portfolio.models.Skill;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.repositories.SkillRepository;
import org.example.internintelligence_portfolio.services.SkillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ApiResponse addSkill(SkillCreateDto skillCreateDto) {
        Skill skill = modelMapper.map(skillCreateDto, Skill.class);
        skillRepository.save(skill);
        return new ApiResponse(true, "Skill added successfully");
    }

    @Override
    public List<SkillDto> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        List<SkillDto> skillDtos = skills.stream().map(skill -> modelMapper.map(skill, SkillDto.class)).collect(Collectors.toList());
        return skillDtos;
    }

    @Override
    public ApiResponse getSkillById(int id) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);

        if (optionalSkill.isEmpty()) {
            return new ApiResponse(false, "Skill not found with id: " + id);
        }
        SkillDto skillDto = modelMapper.map(optionalSkill.get(), SkillDto.class);
        return new ApiResponse(true, "Skill found", skillDto);
    }


    @Override
    public ApiResponse updateSkill(Integer id,SkillUpdateDto skillUpdateDto) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);

        if (optionalSkill.isEmpty()) {
            return new ApiResponse(false, "Project not found with id: " + id);
        }
        Skill skill = optionalSkill.get();

        if(skillUpdateDto.getName() != null && !skillUpdateDto.getName().isEmpty() && !skillUpdateDto.getName().isBlank()){
            skill.setName(skillUpdateDto.getName());
        }

        if (skillUpdateDto.getProficiency() != null && skillUpdateDto.getProficiency() < 100) {
            skill.setProficiency(skillUpdateDto.getProficiency());
        }
        skillRepository.save(skill);
        return new ApiResponse(true, "Skill updated successfully");
    }

    @Override
    public ApiResponse deleteSkill(Integer id) {
        Optional<Skill> optionalSkill= skillRepository.findById(id);
        if (optionalSkill.isEmpty()) {
            return new ApiResponse(false, "Skill not found with id: " + id);
        }
        skillRepository.delete(optionalSkill.get());
        return new ApiResponse(true, "Skill deleted successfully");
    }

}

package org.example.internintelligence_portfolio.services.impl;

import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillCreateDto;
import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillDto;
import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillUpdateDto;
import org.example.internintelligence_portfolio.models.Skill;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.repositories.SkillRepository;
import org.example.internintelligence_portfolio.services.SkillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public SkillDto getSkillById(int id) {
        Skill skill = skillRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Skill not found with id " + id));
        SkillDto skillDto = modelMapper.map(skill, SkillDto.class);
        return skillDto;
    }


    @Override
    public ApiResponse updateSkill(Integer id,SkillUpdateDto skillUpdateDto) {
        Skill skill = skillRepository.findById(id).orElseThrow();
        if(skillUpdateDto.getName() != null || !skillUpdateDto.getName().isEmpty()){
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
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Skill not found with id: " + id));
        skillRepository.delete(skill);
        return new ApiResponse(true, "Skill deleted successfully");
    }

}

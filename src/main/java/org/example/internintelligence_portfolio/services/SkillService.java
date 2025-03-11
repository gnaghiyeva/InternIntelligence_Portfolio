package org.example.internintelligence_portfolio.services;

import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillCreateDto;
import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillDto;
import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillUpdateDto;
import org.example.internintelligence_portfolio.payloads.ApiResponse;

import java.util.List;

public interface SkillService {
    ApiResponse addSkill(SkillCreateDto skillCreateDto);
    List<SkillDto> getAllSkills();
    ApiResponse getSkillById(int id);
    ApiResponse updateSkill(Integer id, SkillUpdateDto skillUpdateDto);
    ApiResponse deleteSkill(Integer id);
}

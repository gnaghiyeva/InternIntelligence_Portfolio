package org.example.internintelligence_portfolio.services.impl;

import org.example.internintelligence_portfolio.dtos.achievementsdtos.AchievementCreateDto;
import org.example.internintelligence_portfolio.dtos.achievementsdtos.AchievementDto;
import org.example.internintelligence_portfolio.dtos.achievementsdtos.AchievementUpdateDto;
import org.example.internintelligence_portfolio.models.Achievement;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.repositories.AchievementRepository;
import org.example.internintelligence_portfolio.services.AchievementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementServiceImpl implements AchievementService {
   @Autowired
   private AchievementRepository achievementRepository;

   @Autowired
   private ModelMapper modelMapper;

    @Override
    public ApiResponse createAchievement(AchievementCreateDto achievementCreateDto) {
        Achievement achievement = modelMapper.map(achievementCreateDto, Achievement.class);
        achievementRepository.save(achievement);
        return new ApiResponse(true, "Achievement created");
    }

    @Override
    public List<AchievementDto> getAchievements() {
        List<Achievement> achievements = achievementRepository.findAll();
        List<AchievementDto> achievementDtos = achievements.stream().map(achievement -> modelMapper.map(achievement, AchievementDto.class)).collect(Collectors.toList());
        return achievementDtos;
    }

    @Override
    public AchievementDto findAchievementById(Long id) {
        Achievement achievement = achievementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Achievement not found with id: " + id));
        AchievementDto achievementDto = modelMapper.map(achievement, AchievementDto.class);
        return achievementDto;
    }

    @Override
    public ApiResponse updateAchievementById(Long id, AchievementUpdateDto achievementUpdateDto) {
        Achievement achievement = achievementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Achievement not found with id: " + id));
        if(achievementUpdateDto.getTitle() != null || !achievementUpdateDto.getTitle().isEmpty()){
            achievement.setTitle(achievementUpdateDto.getTitle());
        }
        if(achievementUpdateDto.getDescription() != null || !achievementUpdateDto.getDescription().isEmpty()){
            achievement.setDescription(achievementUpdateDto.getDescription());
        }
        if (achievementUpdateDto.getDate() != null) {
            achievement.setDate(achievementUpdateDto.getDate());
        }

        achievementRepository.save(achievement);
        return new ApiResponse(true, "Achievement updated");
    }

    @Override
    public ApiResponse deleteAchievement(Long id) {
        Achievement achievement = achievementRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Achievement not found with id: " + id));
        achievementRepository.delete(achievement);
        return new ApiResponse(true, "Achievement deleted");
    }
}

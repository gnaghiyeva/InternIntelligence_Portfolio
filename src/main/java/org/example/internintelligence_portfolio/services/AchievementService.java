package org.example.internintelligence_portfolio.services;

import org.example.internintelligence_portfolio.dtos.achievementsdtos.AchievementCreateDto;
import org.example.internintelligence_portfolio.dtos.achievementsdtos.AchievementDto;
import org.example.internintelligence_portfolio.dtos.achievementsdtos.AchievementUpdateDto;
import org.example.internintelligence_portfolio.payloads.ApiResponse;

import java.util.List;

public interface AchievementService {
    ApiResponse createAchievement(AchievementCreateDto achievementCreateDto);
    List<AchievementDto> getAchievements();
    AchievementDto findAchievementById(Long id);
    ApiResponse updateAchievementById(Long id, AchievementUpdateDto achievementUpdateDto);
    ApiResponse deleteAchievement(Long id);
}

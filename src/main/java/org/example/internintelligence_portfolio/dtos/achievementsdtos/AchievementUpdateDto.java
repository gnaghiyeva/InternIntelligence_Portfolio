package org.example.internintelligence_portfolio.dtos.achievementsdtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AchievementUpdateDto {
    private String title;
    private String description;
    private LocalDate date;
}

package org.example.internintelligence_portfolio.dtos.achievementsdtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AchievementDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
}

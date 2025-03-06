package org.example.internintelligence_portfolio.dtos.maindtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDto {
    private String title;
    private String description;
    private String url;
    private LocalDate startDate;
    private LocalDate endDate;
}

package org.example.internintelligence_portfolio.dtos.projectdtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDto {
    private Long id;
    private String title;
    private String description;
    private String url;
    private LocalDate startDate;
    private LocalDate endDate;
}

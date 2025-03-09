package org.example.internintelligence_portfolio.dtos.projectdtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjectUpdateDto {
    private String title;
    private String description;
    private String url;
    private LocalDate startDate;
    private LocalDate endDate;
}

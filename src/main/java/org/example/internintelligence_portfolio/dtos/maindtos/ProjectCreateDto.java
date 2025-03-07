package org.example.internintelligence_portfolio.dtos.maindtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjectCreateDto {
    private String title;
    private String description;
    private String url;
    private LocalDate startDate;
    private LocalDate endDate;
}

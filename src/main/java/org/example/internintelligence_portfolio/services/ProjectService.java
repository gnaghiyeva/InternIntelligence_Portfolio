package org.example.internintelligence_portfolio.services;

import org.example.internintelligence_portfolio.dtos.maindtos.ProjectDto;
import org.example.internintelligence_portfolio.payloads.ApiResponse;

public interface ProjectService{
    ApiResponse createProject(ProjectDto projectDto);
}

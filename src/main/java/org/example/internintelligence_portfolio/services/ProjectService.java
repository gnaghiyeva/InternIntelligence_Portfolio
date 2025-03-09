package org.example.internintelligence_portfolio.services;

import org.example.internintelligence_portfolio.dtos.projectdtos.ProjectCreateDto;
import org.example.internintelligence_portfolio.dtos.projectdtos.ProjectDto;
import org.example.internintelligence_portfolio.dtos.projectdtos.ProjectUpdateDto;
import org.example.internintelligence_portfolio.payloads.ApiResponse;

import java.util.List;

public interface ProjectService{
    ApiResponse createProject(ProjectCreateDto projectCreateDto);
    List<ProjectDto> getAllProjects();
    ProjectDto findProjectById(Long id);
    ApiResponse updateProjectById(Long id, ProjectUpdateDto projectUpdateDto);
    ApiResponse deleteProject(Long id);
}

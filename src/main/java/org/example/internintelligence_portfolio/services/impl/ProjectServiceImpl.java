package org.example.internintelligence_portfolio.services.impl;


import org.example.internintelligence_portfolio.dtos.maindtos.ProjectCreateDto;
import org.example.internintelligence_portfolio.dtos.maindtos.ProjectDto;
import org.example.internintelligence_portfolio.dtos.maindtos.ProjectUpdateDto;
import org.example.internintelligence_portfolio.models.Project;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.repositories.ProjectRepository;
import org.example.internintelligence_portfolio.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse createProject(ProjectCreateDto projectCreateDto) {
        Project project = modelMapper.map(projectCreateDto, Project.class);
        projectRepository.save(project);
        return new ApiResponse(true, "Project created");
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDto> projectDtos = projects.stream().map(project -> modelMapper.map(project,ProjectDto.class)).collect(Collectors.toList());
        return projectDtos;
    }

    @Override
    public ProjectDto findProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
        ProjectDto projectDto = modelMapper.map(project,ProjectDto.class);
        return projectDto;
    }

    @Override
    public ApiResponse updateProjectById(Long id, ProjectUpdateDto projectUpdateDto) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
        if (projectUpdateDto.getTitle() != null || !projectUpdateDto.getTitle().isEmpty()) {
            project.setTitle(projectUpdateDto.getTitle());
        }
        if (projectUpdateDto.getDescription() != null || !projectUpdateDto.getDescription().isEmpty()) {
            project.setDescription(projectUpdateDto.getDescription());
        }
        if (projectUpdateDto.getUrl() != null || !projectUpdateDto.getUrl().isEmpty()) {
            project.setUrl(projectUpdateDto.getUrl());
        }
        if (projectUpdateDto.getStartDate() != null) {
            project.setStartDate(projectUpdateDto.getStartDate());
        }
        if (projectUpdateDto.getEndDate() != null) {
            project.setEndDate(projectUpdateDto.getEndDate());
        }
        projectRepository.save(project);
        return new ApiResponse(true, "Project updated");
    }

    @Override
    public ApiResponse deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
        projectRepository.delete(project);
        return new ApiResponse(true, "Project deleted");
    }
}

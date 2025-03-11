package org.example.internintelligence_portfolio.services.impl;


import org.example.internintelligence_portfolio.dtos.projectdtos.ProjectCreateDto;
import org.example.internintelligence_portfolio.dtos.projectdtos.ProjectDto;
import org.example.internintelligence_portfolio.dtos.projectdtos.ProjectUpdateDto;
import org.example.internintelligence_portfolio.models.Project;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.repositories.ProjectRepository;
import org.example.internintelligence_portfolio.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
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
    public ApiResponse findProjectById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isEmpty()) {
            return new ApiResponse(false, "Project not found with id: " + id);
        }

        ProjectDto projectDto = modelMapper.map(optionalProject.get(), ProjectDto.class);
        return new ApiResponse(true, "Project found", projectDto);
    }


    @Override
    public ApiResponse updateProjectById(Long id, ProjectUpdateDto projectUpdateDto) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isEmpty()) {
            return new ApiResponse(false, "Project not found with id: " + id);
        }
        Project project = optionalProject.get();

        if (projectUpdateDto.getTitle() != null && !projectUpdateDto.getTitle().isEmpty() && !projectUpdateDto.getTitle().isBlank()) {
            project.setTitle(projectUpdateDto.getTitle());
        }
        if (projectUpdateDto.getDescription() != null && !projectUpdateDto.getDescription().isEmpty() && !projectUpdateDto.getDescription().isBlank()) {
            project.setDescription(projectUpdateDto.getDescription());
        }
        if (projectUpdateDto.getUrl() != null && !projectUpdateDto.getUrl().isEmpty() && !projectUpdateDto.getUrl().isBlank()) {
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
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isEmpty()) {
            return new ApiResponse(false, "Project not found with id: " + id);
        }
        projectRepository.delete(optionalProject.get());
        return new ApiResponse(true, "Project deleted successfully");
    }

}

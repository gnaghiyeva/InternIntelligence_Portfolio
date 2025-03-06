package org.example.internintelligence_portfolio.services.impl;


import org.example.internintelligence_portfolio.dtos.maindtos.ProjectDto;
import org.example.internintelligence_portfolio.models.Project;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.repositories.ProjectRepository;
import org.example.internintelligence_portfolio.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse createProject(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        projectRepository.save(project);
        return new ApiResponse(true, "Project created");
    }
}

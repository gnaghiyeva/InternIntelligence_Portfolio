package org.example.internintelligence_portfolio.controllers.maincontroller;

import org.example.internintelligence_portfolio.dtos.maindtos.ProjectCreateDto;
import org.example.internintelligence_portfolio.dtos.maindtos.ProjectDto;
import org.example.internintelligence_portfolio.dtos.maindtos.ProjectUpdateDto;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> createProject(@ModelAttribute ProjectCreateDto projectCreateDto) {
        ApiResponse response = projectService.createProject(projectCreateDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getProjects() {
        List<ProjectDto> response = projectService.getAllProjects();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/id")
    public ProjectDto getProjectById(@RequestParam Long id) {
        ProjectDto projectDetail = projectService.findProjectById(id);
        return projectDetail;
    }

    @PutMapping(value = "/id",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<ApiResponse> updateProject(@RequestParam Long id, @ModelAttribute ProjectUpdateDto projectUpdateDto) {
        ApiResponse response = projectService.updateProjectById(id, projectUpdateDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/id")
    public ResponseEntity<ApiResponse> deleteProject(@RequestParam Long id) {
        ApiResponse response = projectService.deleteProject(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
package com.group.code.projectmanagement.controller;

import com.group.code.projectmanagement.exception.ValidatorException;
import com.group.code.projectmanagement.model.dto.PostProjectDTO;
import com.group.code.projectmanagement.model.dto.ProjectDTO;
import com.group.code.projectmanagement.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectDTO> getAll() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable Long id) {
        Optional<ProjectDTO> projectDTO = projectService.getProjectById(id);
        return projectDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PostProjectDTO projectDTO) {
        try {
            projectService.createProject(projectDTO);
            return ResponseEntity.ok().build();
        } catch (ValidatorException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok().build();
        } catch (ValidatorException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}

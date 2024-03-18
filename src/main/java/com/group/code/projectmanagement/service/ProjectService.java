package com.group.code.projectmanagement.service;

import com.group.code.projectmanagement.model.dto.ProjectDTO;
import com.group.code.projectmanagement.model.entities.Project;
import com.group.code.projectmanagement.repository.ProjectRepository;
import com.group.code.projectmanagement.util.MapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepository repository;

    MapperUtils modelMapper = new MapperUtils();

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<ProjectDTO> getAll() {
        List<Project> projects = repository.findAll();
        return modelMapper.mapList(projects, ProjectDTO.class);
    }

    public Optional<ProjectDTO> getById(Long id) {
        Optional<Project> optProject = repository.findById(id);

        if (optProject.isPresent()) {
            return Optional.of(modelMapper.map(optProject, ProjectDTO.class));
        } else {
            return Optional.empty();
        }
    }

    public void createProject(ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
        repository.save(project);
    }

}

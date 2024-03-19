package com.group.code.projectmanagement.service;

import com.group.code.projectmanagement.exception.ValidatorException;
import com.group.code.projectmanagement.model.dto.PostProjectDTO;
import com.group.code.projectmanagement.model.dto.ProjectDTO;
import com.group.code.projectmanagement.model.entities.Project;
import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import com.group.code.projectmanagement.repository.ProjectRepository;
import com.group.code.projectmanagement.util.MapperUtils;
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

    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = repository.findAll();
        return modelMapper.mapList(projects, ProjectDTO.class);
    }

    public Optional<ProjectDTO> getProjectById(Long id) {
        Optional<Project> optProject = repository.findById(id);

        if (optProject.isPresent()) {
            return Optional.of(modelMapper.map(optProject, ProjectDTO.class));
        } else {
            return Optional.empty();
        }
    }

    public void createProject(PostProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
        repository.save(project);
    }

    public void deleteProject(Long id) {
        Optional<Project> optProject = repository.findById(id);

        if (optProject.isPresent()) {
            Project project = optProject.get();

            if (ProjectStatusEnum.getStatusCannotDelete().contains(project.getStatus())) {
                throw new ValidatorException("Projeto iniciado, em andamento ou encerrado não pode ser excluído!");
            }

            repository.delete(project);
        }
    }

}

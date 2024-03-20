package com.group.code.projectmanagement.service;

import com.group.code.projectmanagement.controller.project.request.PatchProjectDTO;
import com.group.code.projectmanagement.controller.project.request.PostProjectDTO;
import com.group.code.projectmanagement.controller.project.response.ProjectDTO;
import com.group.code.projectmanagement.exception.ValidatorException;
import com.group.code.projectmanagement.model.entities.Person;
import com.group.code.projectmanagement.model.entities.Project;
import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import com.group.code.projectmanagement.repository.PersonRepository;
import com.group.code.projectmanagement.repository.ProjectRepository;
import com.group.code.projectmanagement.util.MapperUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final PersonRepository personRepository;

    MapperUtils modelMapper = new MapperUtils();

    public ProjectService(ProjectRepository repository,
                          PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
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

    public ProjectDTO createProject(PostProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
        repository.save(project);
        return modelMapper.map(project, ProjectDTO.class);
    }

    public void updateProject(Long id, PostProjectDTO projectDTO) {
        Optional<Project> optProject = repository.findById(id);

        if (optProject.isPresent()) {
            Project project = optProject.get();
            BeanUtils.copyProperties(projectDTO, project);
            repository.save(project);
        }
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

    public void addMembersToProject(Long id, PatchProjectDTO projectDTO) {
        Optional<Project> optProject = repository.findById(id);

        if (optProject.isPresent()) {
            Project project = optProject.get();
            projectDTO.getMembros().forEach(memberId -> {
                Optional<Person> optPerson = personRepository.findById(memberId);

                if (optPerson.isPresent() && optPerson.get().getFuncionario() && !optPerson.get().getGerente()) {
                    project.getMembros().add(optPerson.get());
                }
            });

            repository.save(project);
        }
    }

    public PostProjectDTO initProject(Long id) {
        if (id == null) {
            return new PostProjectDTO();
        } else {
            Optional<Project> optProject = repository.findById(id);
            return modelMapper.map(optProject, PostProjectDTO.class);
        }
    }

}

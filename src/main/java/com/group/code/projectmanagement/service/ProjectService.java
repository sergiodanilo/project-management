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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void upsertProject(PostProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
        Optional<Person> optGerente = personRepository.findById(projectDTO.getIdGerente());
        optGerente.ifPresent(project::setGerente);

        if (Objects.nonNull(projectDTO.getId())) {
            Optional<Project> optProject = repository.findById(projectDTO.getId());
            optProject.ifPresent(value -> project.setMembros(value.getMembros()));
        }

        repository.save(project);
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

    public void addMembersToProject(Long id, PatchProjectDTO members) {
        Optional<Project> optProject = repository.findById(id);

        if (optProject.isPresent()) {
            Project project = optProject.get();
            members.getMembros()
                    .stream()
                    .distinct()
                    .forEach(memberId -> {
                Optional<Person> optPerson = personRepository.findById(memberId);

                if (optPerson.isPresent() && optPerson.get().getFuncionario() && !optPerson.get().getGerente()) {
                    project.getMembros().add(optPerson.get());
                }
            });

            repository.save(project);
        }
    }

    public ProjectDTO initProject(Long id) {
        if (id == null) {
            return new ProjectDTO();
        } else {
            Optional<Project> optProject = repository.findById(id);
            return modelMapper.map(optProject, ProjectDTO.class);
        }
    }

}

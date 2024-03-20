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
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void testGetAllProjects() {
        List<Project> projects = Arrays.asList(new Project(), new Project());
        when(projectRepository.findAll()).thenReturn(projects);

        List<ProjectDTO> result = projectService.getAllProjects();

        assertEquals(2, result.size());
    }

    @Test
    void testGetProjectById_ExistingId() {
        Long id = 1L;
        Project mockProject = new Project();
        Optional<Project> optionalProject = Optional.of(mockProject);
        when(projectRepository.findById(id)).thenReturn(optionalProject);

        Optional<ProjectDTO> result = projectService.getProjectById(id);

        assertTrue(result.isPresent());
    }

    @Test
    void testGetProjectById_NonExistingId() {
        Long id = 1L;
        Optional<Project> optionalProject = Optional.empty();
        when(projectRepository.findById(id)).thenReturn(optionalProject);

        Optional<ProjectDTO> result = projectService.getProjectById(id);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateProject() {
        PostProjectDTO projectDTO = PostProjectDTO.builder().nome("Project").descricao("Project desc").build();
        Project mockProject = new Project();

        when(projectRepository.save(any())).thenReturn(mockProject);

        projectService.upsertProject(projectDTO);
    }

    @Test
    void testUpdateProject() {
        Long id = 1L;
        PostProjectDTO postProjectDTO = new PostProjectDTO();
        Project mockProject = new Project();
        Optional<Project> optionalProject = Optional.of(mockProject);
        when(projectRepository.findById(id)).thenReturn(optionalProject);

        assertDoesNotThrow(() -> projectService.updateProject(id, postProjectDTO));
    }

    @Test
    void testDeleteProject() {
        Long id = 1L;
        Project mockProject = new Project();
        Optional<Project> optionalProject = Optional.of(mockProject);
        when(projectRepository.findById(id)).thenReturn(optionalProject);

        assertDoesNotThrow(() -> projectService.deleteProject(id));
    }

    @Test
    void testDeleteProject_ExceptionThrown() {
        Long id = 1L;
        Project mockProject = new Project();
        mockProject.setStatus(ProjectStatusEnum.INICIADO);
        Optional<Project> optionalProject = Optional.of(mockProject);
        when(projectRepository.findById(id)).thenReturn(optionalProject);

        ValidatorException ex = assertThrows(ValidatorException.class, () -> projectService.deleteProject(id));

        assertEquals(ex.getMessage(), "Projeto iniciado, em andamento ou encerrado não pode ser excluído!");
    }

    @Test
    void testAddMembersToProject() {
        Long id = 1L;
        PatchProjectDTO patchProjectDTO = new PatchProjectDTO();
        patchProjectDTO.setMembros(Arrays.asList(1L, 2L));
        Project mockProject = new Project();
        mockProject.setMembros(Sets.newSet());
        Optional<Project> optionalProject = Optional.of(mockProject);
        when(projectRepository.findById(id)).thenReturn(optionalProject);

        Person person1 = new Person();
        person1.setFuncionario(true);
        person1.setGerente(false);
        Person person2 = new Person();
        person2.setFuncionario(true);
        person2.setGerente(false);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person1));
        when(personRepository.findById(2L)).thenReturn(Optional.of(person2));

        assertDoesNotThrow(() -> projectService.addMembersToProject(id, patchProjectDTO));
    }
}

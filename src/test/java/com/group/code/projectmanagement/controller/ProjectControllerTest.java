package com.group.code.projectmanagement.controller;

import com.group.code.projectmanagement.controller.project.ProjectController;
import com.group.code.projectmanagement.controller.project.request.PostProjectDTO;
import com.group.code.projectmanagement.controller.project.response.ProjectDTO;
import com.group.code.projectmanagement.exception.ValidatorException;
import com.group.code.projectmanagement.service.PersonService;
import com.group.code.projectmanagement.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProjectControllerTest {

    @Test
    public void testListProject() {
        ProjectService projectService = Mockito.mock(ProjectService.class);
        Model model = Mockito.mock(Model.class);

        ProjectController controller = new ProjectController(projectService, null);
        ArrayList<ProjectDTO> projects = new ArrayList<>();
        Mockito.when(projectService.getAllProjects()).thenReturn(projects);

        String viewName = controller.listProject(model);

        assertEquals("project/list", viewName);
        verify(model).addAttribute("projects", projects);
    }

    @Test
    public void testCreateProject() {
        ProjectService projectService = Mockito.mock(ProjectService.class);
        PersonService personService = Mockito.mock(PersonService.class);
        Model model = Mockito.mock(Model.class);

        ProjectController controller = new ProjectController(projectService, personService);
        ProjectDTO projectDTO = new ProjectDTO();
        Mockito.when(projectService.initProject(anyLong())).thenReturn(projectDTO);

        String viewName = controller.createProject(1L, model);

        assertEquals("project/upsert", viewName);
        verify(model).addAttribute("project", projectDTO);
    }

    @Test
    public void testCreateProjectPost() {
        ProjectService projectService = Mockito.mock(ProjectService.class);
        PostProjectDTO projectDTO = new PostProjectDTO();

        ProjectController controller = new ProjectController(projectService, null);

        String viewName = controller.createProject(projectDTO);

        assertEquals("home", viewName);
        verify(projectService).upsertProject(projectDTO);
    }

    @Test
    public void testDelete() {
        // Create mock of ProjectService
        ProjectService projectService = Mockito.mock(ProjectService.class);

        // Set up test data
        ProjectController controller = new ProjectController(projectService, null);
        Long projectId = 1L;

        // Set up behavior of mock
        doNothing().when(projectService).deleteProject(anyLong());

        // Call the method to be tested
        String viewName = controller.delete(projectId);

        // Verify the behavior
        assertEquals("home", viewName);
        verify(projectService).deleteProject(projectId);
    }

    @Test
    public void testDeleteWithError() {
        // Create mock of ProjectService
        ProjectService projectService = Mockito.mock(ProjectService.class);

        // Set up test data
        ProjectController controller = new ProjectController(projectService, null);
        Long projectId = 1L;

        // Set up behavior of mock
        doThrow(new ValidatorException("Error message")).when(projectService).deleteProject(anyLong());

        // Call the method to be tested
        String viewName = controller.delete(projectId);

        // Verify the behavior
        assertEquals("error", viewName);
    }

}

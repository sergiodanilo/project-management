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

        ProjectController controller = new ProjectController(projectService, personService);
        ProjectDTO projectDTO = new ProjectDTO();
        Mockito.when(projectService.initProject(anyLong())).thenReturn(projectDTO);

        String viewName = controller.createProject(new PostProjectDTO());

        assertEquals("home", viewName);
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
        ProjectService projectService = Mockito.mock(ProjectService.class);

        ProjectController controller = new ProjectController(projectService, null);
        Long projectId = 1L;

        doNothing().when(projectService).deleteProject(anyLong());

        String viewName = controller.delete(projectId);

        assertEquals("home", viewName);
        verify(projectService).deleteProject(projectId);
    }

    @Test
    public void testDeleteWithError() {
        ProjectService projectService = Mockito.mock(ProjectService.class);

        ProjectController controller = new ProjectController(projectService, null);
        Long projectId = 1L;

        doThrow(new ValidatorException("Error message")).when(projectService).deleteProject(anyLong());

        String viewName = controller.delete(projectId);

        assertEquals("project/error-delete", viewName);
    }

}

package com.group.code.projectmanagement.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group.code.projectmanagement.controller.project.ProjectController;
import com.group.code.projectmanagement.controller.project.request.PatchProjectDTO;
import com.group.code.projectmanagement.controller.project.request.PostProjectDTO;
import com.group.code.projectmanagement.controller.project.response.ProjectDTO;
import com.group.code.projectmanagement.exception.ValidatorException;
import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import com.group.code.projectmanagement.service.PersonService;
import com.group.code.projectmanagement.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Optional;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;


    @MockBean
    private PersonService personService;

    @Test
    void testGetAll() throws Exception {
        // Mock the service method
        Mockito.when(projectService.getAllProjects()).thenReturn(Collections.singletonList(createProjectDTO()));

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/projects"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());
    }

    @Test
    void testGetById_ExistingId() throws Exception {
        // Mock the service method
        Long id = 1L;
        Mockito.when(projectService.getProjectById(id)).thenReturn(Optional.of(createProjectDTO()));

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/projects/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void testGetById_NonExistingId() throws Exception {
        // Mock the service method
        Long id = 1L;
        Mockito.when(projectService.getProjectById(id)).thenReturn(Optional.empty());

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/projects/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testCreate_ValidInput() throws Exception {
        // Mock the service method
        PostProjectDTO postProjectDTO = createPostProjectDTO();
        ProjectDTO mockProjectDTO = createProjectDTO();
        Mockito.when(projectService.createProject(postProjectDTO)).thenReturn(mockProjectDTO);

        // Perform POST request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Project Name\", \"descricao\": \"Project Description\", \"dataInicio\": \"01/01/2024\"" +
                                ", \"dataPrevisaoFim\": \"01/01/2024\", \"dataFim\": \"01/01/2024\", \"status\": \"INICIADO\"" +
                                ", \"orcamento\": \"10.00\", \"idGerente\": \"1\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void testCreate_InvalidInput() throws Exception {
        // Mock the service method to throw ValidatorException
        PostProjectDTO postProjectDTO = createPostProjectDTO();
        String errorMessage = "[{\"field\":\"descricao\",\"message\":\"Field descricao cannot be null\"}]";;
        Mockito.when(projectService.createProject(postProjectDTO)).thenThrow(new ValidatorException(errorMessage));

        // Perform POST request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Project Name\", \"dataInicio\": \"01/01/2024\"" +
                                ", \"dataPrevisaoFim\": \"01/01/2024\", \"dataFim\": \"01/01/2024\", \"status\": \"INICIADO\"" +
                                ", \"orcamento\": \"10.00\", \"idGerente\": \"1\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));
    }

    @Test
    void testUpdate_ValidInput() throws Exception {
        // Mock the service method
        Long id = 1L;
        PostProjectDTO postProjectDTO = createPostProjectDTO();
        Mockito.doNothing().when(projectService).updateProject(id, postProjectDTO);

        // Perform PUT request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.put("/projects/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Project Name\", \"descricao\": \"Project Description\", \"dataInicio\": \"01/01/2024\"" +
                                ", \"dataPrevisaoFim\": \"01/01/2024\", \"dataFim\": \"01/01/2024\", \"status\": \"INICIADO\"" +
                                ", \"orcamento\": \"10.00\", \"idGerente\": \"1\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdate_InvalidInput() throws Exception {
        // Mock the service method to throw ValidatorException
        Long id = 1L;
        PostProjectDTO postProjectDTO = createInvalidPostProjectDTO();
        String errorMessage = "[{\"field\":\"nome\",\"message\":\"Field nome cannot be null\"}]";
        Mockito.doThrow(new ValidatorException(errorMessage)).when(projectService).updateProject(id, postProjectDTO);

        // Perform PUT request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.put("/projects/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Project Description\", \"dataInicio\": \"01/01/2024\"" +
                                ", \"dataPrevisaoFim\": \"01/01/2024\", \"dataFim\": \"01/01/2024\", \"status\": \"INICIADO\"" +
                                ", \"orcamento\": \"10.00\", \"idGerente\": \"1\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));
    }

    @Test
    void testDelete() throws Exception {
        // Mock the service method
        Long id = 1L;
        Mockito.doNothing().when(projectService).deleteProject(id);

        // Perform DELETE request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddMembersToProject_ValidInput() throws Exception {
        // Mock the service method
        Long id = 1L;
        PatchProjectDTO patchProjectDTO = new PatchProjectDTO();
        Mockito.doNothing().when(projectService).addMembersToProject(id, patchProjectDTO);

        // Perform PATCH request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.patch("/projects/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"members\": [1, 2, 3]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddMembersToProject_InvalidInput() throws Exception {
        // Mock the service method to throw ValidatorException
        Long id = 1L;
        PatchProjectDTO patchProjectDTO = new PatchProjectDTO();
        String errorMessage = "Validation error message";
        Mockito.doThrow(new ValidatorException(errorMessage)).when(projectService).addMembersToProject(id, patchProjectDTO);

        // Perform PATCH request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.patch("/projects/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"members\": [1, 2, 3]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));
    }

    private ProjectDTO createProjectDTO() {
        return ProjectDTO.builder()
                .id(1L)
                .build();
    }

    private PostProjectDTO createPostProjectDTO() {
        return PostProjectDTO.builder()
                .nome("Project Name")
                .descricao("Project Description")
                .dataInicio(LocalDate.of(2024, Month.JANUARY, 1))
                .dataPrevisaoFim(LocalDate.of(2024, Month.JANUARY, 1))
                .dataFim(LocalDate.of(2024, Month.JANUARY, 1))
                .status(ProjectStatusEnum.INICIADO)
                .orcamento(10.00)
                .idGerente(1L)
                .build();
    }

    private PostProjectDTO createInvalidPostProjectDTO() {
        return PostProjectDTO.builder()
                .nome("Project Name")
                .descricao("Project Description")
                .build();
    }

}

package com.group.code.projectmanagement.controller;

import com.group.code.projectmanagement.controller.person.PersonController;
import com.group.code.projectmanagement.controller.person.request.PostPersonDTO;
import com.group.code.projectmanagement.controller.person.response.PersonDTO;
import com.group.code.projectmanagement.exception.ValidatorException;
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

import java.util.Collections;
import java.util.Optional;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;


    @MockBean
    private ProjectService projectService;

    @Test
    void testGetAll() throws Exception {
        // Mock the service method
        Mockito.when(personService.getAllMembers()).thenReturn(Collections.singletonList(
                createPersonDTO()
        ));

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/members"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());
    }

    @Test
    void testGetById_ExistingId() throws Exception {
        // Mock the service method
        Long id = 1L;
        Mockito.when(personService.getMemberById(id)).thenReturn(Optional.of(createPersonDTO()));

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/members/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void testGetById_NonExistingId() throws Exception {
        // Mock the service method
        Long id = 1L;
        Mockito.when(personService.getMemberById(id)).thenReturn(Optional.empty());

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/members/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testCreate_ValidInput() throws Exception {
        // Mock the service method
        PostPersonDTO postPersonDTO = PostPersonDTO.builder().nome("John").funcionario(true).gerente(false).build();
        PersonDTO mockPersonDTO = createPersonDTO();
        Mockito.when(personService.createMember(postPersonDTO)).thenReturn(mockPersonDTO);

        // Perform POST request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"John\", \"funcionario\": true, \"gerente\": false}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void testCreate_InvalidInput() throws Exception {
        // Mock the service method to throw ValidatorException
        PostPersonDTO postPersonDTO = PostPersonDTO.builder().nome("John").funcionario(true).gerente(false).build();
        String errorMessage = "Validation error message";
        Mockito.when(personService.createMember(postPersonDTO)).thenThrow(new ValidatorException(errorMessage));

        // Perform POST request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"John\", \"funcionario\": true, \"gerente\": false}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));
    }

    private PersonDTO createPersonDTO() {
        return PersonDTO.builder().id(1L).build();
    }

}

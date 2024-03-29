package com.group.code.projectmanagement.service;

import com.group.code.projectmanagement.controller.person.request.PostPersonDTO;
import com.group.code.projectmanagement.controller.person.response.PersonDTO;
import com.group.code.projectmanagement.model.entities.Person;
import com.group.code.projectmanagement.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @Test
    void testGetAllMembers() {
        List<Person> mockPersons = Arrays.asList(
                Person.builder().id(1L).nome("John").dataNascimento(LocalDate.of(1990, 5, 15))
                        .cpf("12345678910").funcionario(true).gerente(false).build(),
                Person.builder().id(2L).nome("Alice").dataNascimento(LocalDate.of(1985, 2, 20))
                        .cpf("987654321").funcionario(true).gerente(false).build()
        );
        when(repository.findAllByFuncionarioTrueAndGerenteFalse()).thenReturn(mockPersons);

        List<PersonDTO> result = service.getAllMembers();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNome());
        assertEquals("Alice", result.get(1).getNome());
    }

    @Test
    void testGetAllManagers() {
        List<Person> mockPersons = Arrays.asList(
                Person.builder().id(1L).nome("John").dataNascimento(LocalDate.of(1990, 5, 15))
                        .cpf("12345678910").funcionario(true).gerente(false).build(),
                Person.builder().id(2L).nome("Alice").dataNascimento(LocalDate.of(1985, 2, 20))
                        .cpf("987654321").funcionario(true).gerente(true).build()
        );
        when(repository.findAllByGerenteTrue()).thenReturn(mockPersons);

        List<PersonDTO> result = service.getAllManagers();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNome());
        assertEquals("Alice", result.get(1).getNome());
    }

    @Test
    void testCreateMember() {
        PostPersonDTO postPersonDTO = PostPersonDTO.builder()
                .nome("Alice")
                .funcionario(true)
                .gerente(false)
                .build();

        when(repository.save(any())).thenAnswer(invocation -> {
            Person savedPerson = invocation.getArgument(0);
            savedPerson.setId(1L); // Simulate ID generation by the repository
            return savedPerson;
        });

        PersonDTO result = service.createMember(postPersonDTO);

        assertEquals("Alice", result.getNome());
        assertNotNull(result.getId()); // ID should not be null after saving
    }

}
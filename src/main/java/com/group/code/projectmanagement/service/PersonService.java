package com.group.code.projectmanagement.service;

import com.group.code.projectmanagement.controller.person.request.PostPersonDTO;
import com.group.code.projectmanagement.controller.person.response.PersonDTO;
import com.group.code.projectmanagement.model.entities.Person;
import com.group.code.projectmanagement.repository.PersonRepository;
import com.group.code.projectmanagement.util.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    MapperUtils mapperUtils = new MapperUtils();

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<PersonDTO> getAllMembers() {
        List<Person> managers = repository.findAllByFuncionarioTrueAndGerenteFalse();
        return mapperUtils.mapList(managers, PersonDTO.class);
    }

    public List<PersonDTO> getAllManagers() {
        List<Person> managers = repository.findAllByGerenteTrue();
        return mapperUtils.mapList(managers, PersonDTO.class);
    }

    public PersonDTO createMember(PostPersonDTO personDTO) {
        Person person = mapperUtils.map(personDTO, Person.class);
        repository.save(person);
        return mapperUtils.map(person, PersonDTO.class);
    }

}

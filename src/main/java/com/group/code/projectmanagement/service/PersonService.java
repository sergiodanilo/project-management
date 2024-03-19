package com.group.code.projectmanagement.service;

import com.group.code.projectmanagement.controller.person.response.PersonDTO;
import com.group.code.projectmanagement.controller.person.request.PostPersonDTO;
import com.group.code.projectmanagement.model.entities.Person;
import com.group.code.projectmanagement.repository.PersonRepository;
import com.group.code.projectmanagement.util.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    MapperUtils modelMapper = new MapperUtils();

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<PersonDTO> getAllMembers() {
        List<Person> projects = repository.findAll();
        return modelMapper.mapList(projects, PersonDTO.class);
    }

    public Optional<PersonDTO> getMemberById(Long id) {
        Optional<Person> optPerson = repository.findById(id);

        if (optPerson.isPresent()) {
            return Optional.of(modelMapper.map(optPerson, PersonDTO.class));
        } else {
            return Optional.empty();
        }
    }

    public PersonDTO createMember(PostPersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        repository.save(person);
        return modelMapper.map(person, PersonDTO.class);
    }

}

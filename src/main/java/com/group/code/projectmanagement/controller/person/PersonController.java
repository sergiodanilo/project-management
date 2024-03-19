package com.group.code.projectmanagement.controller.person;

import com.group.code.projectmanagement.exception.ValidatorException;
import com.group.code.projectmanagement.controller.person.response.PersonDTO;
import com.group.code.projectmanagement.controller.person.request.PostPersonDTO;
import com.group.code.projectmanagement.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<PersonDTO> getAll() {
        return service.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable Long id) {
        Optional<PersonDTO> personDTO = service.getMemberById(id);
        return personDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PostPersonDTO personDTO) {
        try {
            return ResponseEntity.ok().body(service.createMember(personDTO));
        } catch (ValidatorException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}

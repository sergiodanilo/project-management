package com.group.code.projectmanagement.controller.person;

import com.group.code.projectmanagement.controller.person.request.PostPersonDTO;
import com.group.code.projectmanagement.exception.ValidatorException;
import com.group.code.projectmanagement.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/members")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PostPersonDTO personDTO) {
        try {
            return ResponseEntity.ok().body(service.createMember(personDTO));
        } catch (ValidatorException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping(value = "/add/{id}")
    public String addMembers(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("members", service.getAllMembers());
        return "member/add";
    }

}

package com.group.code.projectmanagement.controller.project;

import com.group.code.projectmanagement.controller.person.response.PersonDTO;
import com.group.code.projectmanagement.controller.project.request.PatchProjectDTO;
import com.group.code.projectmanagement.controller.project.request.PostProjectDTO;
import com.group.code.projectmanagement.controller.project.response.ProjectDTO;
import com.group.code.projectmanagement.exception.ValidatorException;
import com.group.code.projectmanagement.model.entities.Person;
import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import com.group.code.projectmanagement.service.PersonService;
import com.group.code.projectmanagement.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final PersonService personService;

    public ProjectController(ProjectService projectService, PersonService personService) {
        this.projectService = projectService;
        this.personService = personService;
    }

    @GetMapping
    public String listProject(Model model) {
        List<ProjectDTO> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "project/list";
    }

    @GetMapping("/upsert")
    public String createProject(Long id, Model model) {
        List<PersonDTO> managers = personService.getAllManagerMember();
        model.addAttribute("project", projectService.initProject(id));
        model.addAttribute("managers", managers);
        model.addAttribute("status", ProjectStatusEnum.values());
        return "project/upsert";
    }

    @PostMapping("create")
    public String createProject(PostProjectDTO projectDTO) {
        try {
            projectService.createProject(projectDTO);
            return "home";
        } catch (ValidatorException exception) {
            return "error";
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable Long id) {
        Optional<ProjectDTO> projectDTO = projectService.getProjectById(id);
        return projectDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public ResponseEntity<?> create(@Valid PostProjectDTO projectDTO) {
//        try {
//            return ResponseEntity.ok().body(projectService.createProject(projectDTO));
//        } catch (ValidatorException exception) {
//            return ResponseEntity.badRequest().body(exception.getMessage());
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PostProjectDTO projectDTO) {
        try {
            projectService.updateProject(id, projectDTO);
            return ResponseEntity.ok().build();
        } catch (ValidatorException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok().build();
        } catch (ValidatorException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> addMembersToProject(@PathVariable Long id, @RequestBody PatchProjectDTO projectDTO) {
        try {
            projectService.addMembersToProject(id, projectDTO);
            return ResponseEntity.ok().build();
        } catch (ValidatorException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}

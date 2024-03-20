package com.group.code.projectmanagement.controller.project;

import com.group.code.projectmanagement.controller.person.response.PersonDTO;
import com.group.code.projectmanagement.controller.project.request.PatchProjectDTO;
import com.group.code.projectmanagement.controller.project.request.PostProjectDTO;
import com.group.code.projectmanagement.controller.project.response.ProjectDTO;
import com.group.code.projectmanagement.exception.ValidatorException;
import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import com.group.code.projectmanagement.service.PersonService;
import com.group.code.projectmanagement.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("upsert")
    public String createProject(PostProjectDTO projectDTO) {
        try {
            projectService.upsertProject(projectDTO);
            return "home";
        } catch (ValidatorException exception) {
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return "home";
        } catch (ValidatorException exception) {
            return "error";
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

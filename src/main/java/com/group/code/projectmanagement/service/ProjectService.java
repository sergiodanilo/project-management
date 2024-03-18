package com.group.code.projectmanagement.service;

import com.group.code.projectmanagement.model.dto.ProjectDTO;
import com.group.code.projectmanagement.model.entities.Project;
import com.group.code.projectmanagement.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    ModelMapper modelMapper = new ModelMapper();


    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> getAll() {
        List<Project> projects = projectRepository.findAll();
        return modelMapper.map(projects, List.class);
    }

}

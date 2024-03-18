package com.group.code.projectmanagement.model.dto;

import com.group.code.projectmanagement.model.entities.Person;
import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ProjectDTO {

    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataPrevisaoFim;
    private LocalDate dataFim;
    private String descricao;
    private ProjectStatusEnum status;
    private Double orcamento;
    private String risco;
    private Person gerente;
    private Set<Person> people;

}

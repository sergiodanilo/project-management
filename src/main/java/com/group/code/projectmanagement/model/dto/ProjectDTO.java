package com.group.code.projectmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProjectDTO implements Serializable {

    private Long id;
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPrevisaoFim;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    private String descricao;
    private ProjectStatusEnum status;
    private Double orcamento;
    private String risco;
    private PersonDTO gerente;
    private Set<PersonDTO> people;

}

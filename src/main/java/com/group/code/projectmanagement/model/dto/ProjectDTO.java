package com.group.code.projectmanagement.model.dto;

import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProjectDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataPrevisaoFim;

    @NotNull
    private LocalDate dataFim;

    @NotNull
    private String descricao;

    @NotNull
    @NotEmpty
    private ProjectStatusEnum status;

    @NonNull
    private Double orcamento;

    private String risco;

    private PersonDTO gerente;

    private Set<PersonDTO> people;

}

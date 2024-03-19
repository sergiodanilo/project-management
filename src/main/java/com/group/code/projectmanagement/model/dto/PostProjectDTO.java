package com.group.code.projectmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostProjectDTO {

    @NotNull(message = "Nome cannot be null")
    private String nome;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPrevisaoFim;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    @NotNull
    private String descricao;

    @NotNull
    private ProjectStatusEnum status;

    @NonNull
    private Double orcamento;

    private PersonDTO gerente;

}




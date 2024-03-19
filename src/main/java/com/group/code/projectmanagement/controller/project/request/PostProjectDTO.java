package com.group.code.projectmanagement.controller.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostProjectDTO {

    @NotNull(message = "Field nome cannot be null")
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

    @NotNull(message = "Field descricao cannot be null")
    private String descricao;

    @NotNull(message = "Field status cannot be null")
    private ProjectStatusEnum status;

    @NotNull(message = "Field orcamento cannot be null")
    private Double orcamento;

    @NotNull(message = "Field idGerente cannot be null")
    private Long idGerente;

}




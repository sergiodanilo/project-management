package com.group.code.projectmanagement.controller.project.request;

import com.group.code.projectmanagement.model.enums.ProjectRiskEnum;
import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostProjectDTO {

    private Long id;

    @NotNull(message = "Field nome cannot be null")
    private String nome;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInicio;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataPrevisaoFim;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFim;

    @NotNull(message = "Field descricao cannot be null")
    private String descricao;

    @NotNull(message = "Field status cannot be null")
    private ProjectStatusEnum status;

    @NotNull(message = "Field orcamento cannot be null")
    private Double orcamento;

    @NotNull(message = "Field idGerente cannot be null")
    private Long idGerente;

    private ProjectRiskEnum risco;

}




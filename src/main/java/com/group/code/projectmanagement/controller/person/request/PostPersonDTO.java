package com.group.code.projectmanagement.controller.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostPersonDTO {

    @NotNull(message = "Field nome cannot be null")
    private String nome;

    @NotNull(message = "Field funcionario cannot be null")
    private Boolean funcionario;

    @NotNull(message = "Field gerente cannot be null")
    private Boolean gerente;

}




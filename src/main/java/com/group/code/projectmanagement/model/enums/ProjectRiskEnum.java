package com.group.code.projectmanagement.model.enums;

import lombok.Getter;

@Getter
public enum ProjectRiskEnum {

    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private final String descricao;

    ProjectRiskEnum(String descricao) {
        this.descricao = descricao;
    }

}

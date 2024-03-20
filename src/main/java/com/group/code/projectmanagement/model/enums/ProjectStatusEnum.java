package com.group.code.projectmanagement.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ProjectStatusEnum {

    EM_ANALISE("Em análise"),
    ANALISE_REALIZADA("Análise realizada"),
    ANALISE_APROVADA("Análise aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private final String descricao;

    ProjectStatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public static List<ProjectStatusEnum> getStatusCannotDelete() {
        return Arrays.asList(INICIADO, EM_ANDAMENTO, ENCERRADO);
    }

}
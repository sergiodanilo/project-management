package com.group.code.projectmanagement.model.enums;

import java.util.Arrays;
import java.util.List;

public enum ProjectStatusEnum {

    EM_ANALISE, ANALISE_REALIZADA, ANALISE, APROVADA, INICIADO, PLANEJADO, EM_ANDAMENTO, ENCERRADO, CANCELADO;

    public static List<ProjectStatusEnum> getStatusCannotDelete() {
        return Arrays.asList(INICIADO, EM_ANDAMENTO, ENCERRADO);
    }

}
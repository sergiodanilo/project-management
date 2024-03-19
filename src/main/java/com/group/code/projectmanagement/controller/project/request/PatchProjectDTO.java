package com.group.code.projectmanagement.controller.project.request;

import lombok.Data;

import java.util.Set;

@Data
public class PatchProjectDTO {

    private Set<Long> membros;

}

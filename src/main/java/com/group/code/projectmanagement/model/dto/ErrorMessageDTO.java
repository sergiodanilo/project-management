package com.group.code.projectmanagement.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorMessageDTO {

    private String field;
    private String message;

}

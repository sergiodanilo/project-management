package com.group.code.projectmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.code.projectmanagement.controller.ProjectController;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
//        Input input = invalidInput();
//        String body = objectMapper.writeValueAsString(input);
//
//        mvc.perform(post("/validateBody")
//                        .contentType("application/json")
//                        .content(body))
//                .andExpect(status().isBadRequest());
    }

}

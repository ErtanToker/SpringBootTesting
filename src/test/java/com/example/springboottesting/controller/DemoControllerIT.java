package com.example.springboottesting.controller;

import com.example.springboottesting.model.Demo;
import com.example.springboottesting.request.DemoRequest;
import com.example.springboottesting.service.DemoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DemoController.class)
public class DemoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DemoService demoService;

    @Test
    @DisplayName("IT - Should return OK and a demo json")
    public void get_demo_by_id_should_return_OK_and_a_demo_json() throws Exception{
        String id = "someId";
        String value = "someValue";

        Demo demo = new Demo();
        demo.setId(id);
        demo.setValue(value);

        Mockito.when(demoService.getDemo(id)).thenReturn(demo);

        this.mockMvc.perform(
                get("/demo/" + id)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.value").value(value));
    }

    @Test
    @DisplayName("IT - Should return CREATED and a demo json")
    public void create_demo_should_return_CREATED_and_a_demo_json() throws Exception{
        String id = "someId";
        String value = "someValue";

        DemoRequest demo = new DemoRequest();
        demo.setValue(value);

        Demo result = new Demo();
        result.setId(id);
        result.setValue(value);

        when(demoService.createDemo(value)).thenReturn(result);

        this.mockMvc.perform(post("/demo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(demo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.value").value(value));

        verify(demoService).createDemo(value);
    }

    @Test
    @DisplayName("IT - Should return OK and a list of demo json")
    public void get_demo_by_id_should_return_OK_and_a_list_of_demo_json() throws Exception{
        String valueQuery = "someValue";

        String id = "someId";
        String id2 = "someId 2";
        String value = "somValue 123";
        String value2 = "someValue 1234";

        Demo demo = new Demo();
        demo.setId(id);
        demo.setValue(value);

        Demo demo2 = new Demo();
        demo2.setId(id2);
        demo2.setValue(value2);

        when(demoService.getDemos(valueQuery)).thenReturn(List.of(demo, demo2));

        this.mockMvc.perform(
                get("/demo?value=" + valueQuery)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].value").value(value))
                .andExpect(jsonPath("$[1].id").value(id2))
                .andExpect(jsonPath("$[1].value").value(value2));
    }

}
package com.ghsong.handlermethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventApiTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createEvent() throws Exception {

        Event event = new Event();
        event.setName("ghsong");
        event.setLimit(20);

        String json = objectMapper.writeValueAsString(event);

        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("ghsong"))
                .andExpect(jsonPath("limit").value("20"))
        ;
    }

    @Test
    public void createEvent3() throws Exception {

        Event event = new Event();
        event.setName("ghsong");
        event.setLimit(-20);

        String json = objectMapper.writeValueAsString(event);

        mockMvc.perform(post("/api/events/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("ghsong"))
                .andExpect(jsonPath("limit").value("-20"))
        ;
    }

    @Test
    public void createEvent4() throws Exception {

        Event event = new Event();
        event.setName("ghsong");
        event.setLimit(-20);

        String json = objectMapper.writeValueAsString(event);

        mockMvc.perform(post("/api/events/4")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("ghsong"))
                .andExpect(jsonPath("limit").value("-20"))
        ;
    }

    @Test
    public void createEvent5() throws Exception {

        Event event = new Event();
        event.setName("ghsong");
        event.setLimit(-20);

        String json = objectMapper.writeValueAsString(event);

        mockMvc.perform(post("/api/events/5")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }
}
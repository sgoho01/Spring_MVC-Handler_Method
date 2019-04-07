package com.ghsong.handlermethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getEvent() throws Exception {
        mockMvc.perform(get("/event/1;name=ghsong"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                ;
    }

    @Test
    public void getEvent2() throws Exception {
        mockMvc.perform(post("/events?name=ghsong&limit=30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("ghsong"))
                ;
        mockMvc.perform(post("/events")
                    .param("name", "ghsong")
                    .param("limit", "50"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("ghsong"))
        ;
    }

    @Test
    public void eventFrom() throws Exception {
        mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(view().name("/events/form"))
                .andExpect(model().attributeExists("event"))
                .andExpect(request().sessionAttribute("event", notNullValue()))
                ;
        MockHttpServletRequest request = mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(view().name("/events/form"))
                .andExpect(model().attributeExists("event"))
                .andExpect(request().sessionAttribute("event", notNullValue()))
                .andReturn().getRequest();
        Object event = request.getSession().getAttribute("event");
        System.out.println(event);
    }

    @Test
    public void getEvents2() throws Exception {
        mockMvc.perform(post("/events2")
                    .param("name", "ghsong")
                    .param("limit", "30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("ghsong"))
                .andExpect(jsonPath("limit").value(30))
                ;
    }
    @Test
    public void getEvents22() throws Exception {
        mockMvc.perform(post("/events22/name/ghsong")
                    .param("limit", "-30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("ghsong"))
//                .andExpect(jsonPath("limit").value(30))
                ;
    }

    @Test
    public void postEvents3() throws Exception {
        mockMvc.perform(post("/events3")
                    .param("name", "ghsong")
                    .param("limit", "-10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                ;
    }
}
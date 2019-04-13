package com.ghsong.handlermethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MultiFormControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void eventsForName() {
    }

    @Test
    public void eventsForNameSubmit() {
    }

    @Test
    public void eventsForLimit() {
    }

    @Test
    public void eventsForLimitSubmit() {
    }

    @Test
    public void getEvents() throws Exception {
        Event newEvent = new Event();
        newEvent.setName("ghsong");
        newEvent.setLimit(10);

        mockMvc.perform(get("/events/list2")
                    .sessionAttr("visitTime", LocalDateTime.now())
                    .flashAttr("newEvent", newEvent))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(xpath("//p").nodeCount(2))
                ;

    }
}
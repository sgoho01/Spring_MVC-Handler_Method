package com.ghsong.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleController {

    @GetMapping("/event/{id}")
    @ResponseBody
    public Event getEvnet(@PathVariable(required = false) int id, @MatrixVariable String name) {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        return event;
    }

    @PostMapping("/events")
    @ResponseBody
    public Event getEvent2(@RequestParam(required = false) String name ,
                           @RequestParam Integer limit,
                           SessionStatus sessionStatus) {
        Event event = new Event();
        event.setName(name);
        event.setLimit(limit);

        sessionStatus.setComplete();
        return event;
    }

    @GetMapping("/events/form")
    public String eventsFrom(Model model) {
        Event event = new Event();
        event.setLimit(50);
        model.addAttribute("events", event);
        return "/events/form";
    }

    @PostMapping("/events2")
    @ResponseBody
    public Event getEvents(@ModelAttribute Event event) {
        return event;
    }

    @PostMapping("/events22/name/{name}")
    @ResponseBody
    public Event getEvents2(@Validated @ModelAttribute Event event, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.out.println("=================");
            bindingResult.getAllErrors().forEach(c -> {
                System.out.println(c.toString());
            });
        }
        return event;
    }

    @PostMapping("/events3")
    public String getEvents3(@Validated @ModelAttribute Event event,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "/events/form";
        }

        // db save 처리

        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model) {
        // db 에서 조회 처리
        Event event = new Event();
        event.setName("ghsong");
        event.setLimit(50);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        model.addAttribute("eventList", eventList);
        return "/events/list";
    }

}

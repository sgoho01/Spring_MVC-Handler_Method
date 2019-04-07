package com.ghsong.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
public class MultiFormController {

    @GetMapping("/events/form/name")
    public String eventsForName(Model model) {
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }

    @PostMapping("/events/form/name")
    public String eventsForNameSubmit(@Validated @ModelAttribute Event event,
                                      BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "/events/form-name";
        }
        return "redirect:/events/form/limit";
    }

    @GetMapping("/events/form/limit")
    public String eventsForLimit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        return "/events/form-limit";
    }

    @PostMapping("/events/form/limit")
    public String eventsForLimitSubmit(@Validated @ModelAttribute Event event,
                                       BindingResult bindingResult,
                                       SessionStatus sessionStatus,
                                       RedirectAttributes attributes) {
        if(bindingResult.hasErrors()){
            return "/events/form-limit";
        }
        sessionStatus.setComplete();
        attributes.addAttribute("name", event.getName());
        attributes.addAttribute("limit", event.getLimit());
        return "redirect:/events/list2";
    }

    @GetMapping("/events/list2")
    public String getEvents(@RequestParam String name,
                            @RequestParam Integer limit,
                            Model model,
                            @SessionAttribute LocalDateTime visitTime) {
        System.out.println(visitTime);
        // db 에서 조회 처리

        Event event0 = new Event();
        event0.setName(name);
        event0.setLimit(limit);

        Event event = new Event();
        event.setName("ghsong");
        event.setLimit(50);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        eventList.add(event0);

        model.addAttribute("eventList", eventList);

        return "/events/list";
    }

}

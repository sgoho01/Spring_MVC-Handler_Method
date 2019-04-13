package com.ghsong.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
public class MultiFormController {

    @InitBinder
    public void InitEventBinder(WebDataBinder webDataBinder) {
        // id 컬럼 값 걸러냄
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute
    public void categories(Model model) {
        //model.addAttribute("catogories", List.of("study", "semina", "hobby"));
    }

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

        // RedirectAttributes 사용 시
//        attributes.addAttribute("name", event.getName());
//        attributes.addAttribute("limit", event.getLimit());

        // Flash Attibutes 사용
        attributes.addFlashAttribute("newEvent", event);

        return "redirect:/events/list2";
    }

    @GetMapping("/events/list2")
    public String getEvents(
//                            @RequestParam String name,
//                            @RequestParam Integer limit,

                            Model model,
                            @SessionAttribute LocalDateTime visitTime) {
        System.out.println(visitTime);
        // db 에서 조회 처리

//        Event event = new Event();
//        event.setName(name);
//        event.setLimit(limit);

        Event spring = new Event();
        spring.setName("ghsong222");
        spring.setLimit(30);

        Event newEvent = (Event) model.asMap().get("newEvent");

        List<Event> eventList = new ArrayList<>();
        eventList.add(spring);
        eventList.add(newEvent);

        model.addAttribute("eventList", eventList);

        return "/events/list";
    }

}

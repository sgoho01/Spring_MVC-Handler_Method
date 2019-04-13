package com.ghsong.handlermethod;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventApi {

    @PostMapping("/1")
    public Event createEvent(@RequestBody  Event event) {
        // save event

        return event;
    }

    @PostMapping("/2")
    public Event createEvent2(HttpEntity<Event> request) {
        // save event
        MediaType contentType = request.getHeaders().getContentType();
        System.out.println(contentType);

        return request.getBody();
    }

    @PostMapping("/3")
    public Event createEvent3(@RequestBody @Valid Event event, BindingResult bindingResult) {
        // save event

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                System.out.println(objectError);
            });
        }

        return event;
    }

    @PostMapping("/4")
    @ResponseBody
    public Event createEvent4(@RequestBody Event event) {

        return event;
    }


    @PostMapping("/5")
    @ResponseBody
    public ResponseEntity<Event> createEvent5(@RequestBody @Valid Event event, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        //return ResponseEntity.ok(event);
        return new ResponseEntity<Event>(event,HttpStatus.CREATED);
    }

}

package com.ghsong.handlermethod;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class Event {

    //interface ValidateLimit{}
    //interface ValidateName{}

    private Integer id;

    @NotBlank//(groups = ValidateName.class)
    private String name;

    //@Min(value = 0, groups = ValidateLimit.class)
    @Min(0)
    private Integer limit;
}

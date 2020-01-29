package pl.tybuszesdev.springsecuity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/hello")                                                          //ADD SPRINGSECURITY TO THIS METHOD
    public String sayHello(){
        return "hello!";
    }

    @GetMapping("/hello2")                                                          //ADD SPRINGSECURITY TO THIS METHOD
    public String sayHello2(){
        return "hello2!";
    }
}

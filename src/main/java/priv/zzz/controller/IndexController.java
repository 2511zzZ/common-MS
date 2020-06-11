package priv.zzz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import priv.zzz.aop.LogAnnotation;

@RestController
@Slf4j
public class IndexController {

    @LogAnnotation()
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String greetings(){
        return "Hello";
    }

    @LogAnnotation()
    @RequestMapping(value = "log", method = RequestMethod.GET)
    public String logTest(@RequestParam String name, @RequestParam int number ) {
        return name + " " + number;
    }

}

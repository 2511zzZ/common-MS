package priv.zzz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class IndexController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String greetings(){
        return "Hello";
    }
}


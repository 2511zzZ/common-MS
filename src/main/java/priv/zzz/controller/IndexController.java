package priv.zzz.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class IndexController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String greetings(){
        return "Hello";
    }

    @RequestMapping(value = "api", method = RequestMethod.GET)
    @ApiOperation(value = "API 页面", notes = "接口列表")
    public void turnToApiPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("doc.html");
    }
}


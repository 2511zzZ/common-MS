package priv.zzz.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.zzz.result.Result;
import priv.zzz.result.ResultSet;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "log", method = RequestMethod.GET)
    public String logTest(@RequestParam String name, @RequestParam int number ) {
        return name + " " + number;
    }

    @RequestMapping(value = "result", method = RequestMethod.GET)
    public Result<Integer> resultTest() {
        return Result.success(123);
    }

    @RequestMapping(value = "result/set", method = RequestMethod.GET)
    public ResultSet<Student> resultSetTest() {
        List<Student> list = Arrays.asList(
                new Student("chen", 20),
                new Student("ming", 21),
                new Student("zoom", 22)
        );
        return ResultSet.success(list.size(), list);
    }

    @RequestMapping(value = "fail", method = RequestMethod.GET)
    public Result<Integer> failTest() {
        return Result.failure();
    }
}


@Data
class Student implements Serializable {

    String name;
    int age;

    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }
}


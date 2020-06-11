package priv.zzz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import priv.zzz.model.TestUser;
import priv.zzz.result.Result;
import priv.zzz.result.ResultCode;
import priv.zzz.result.ResultSet;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@Api("测试接口")
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
    public Result<Integer> failTest(@RequestParam Integer errorCode) {
        if (errorCode == 404){ return Result.failure(ResultCode.NOT_FOUND); }
        if (errorCode == 403){ return Result.failure(ResultCode.UNAUTHORIZED); }
        if (errorCode == 500){ return Result.failure(ResultCode.SERVER_ERROR); }
        return Result.failure();
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ApiOperation(value = "validator测试方法")
    public Result<Integer> saveUser(@RequestBody @Valid TestUser user) {
        // do something
        return Result.success();
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


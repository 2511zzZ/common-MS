package priv.zzz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import priv.zzz.exception.ExampleException;
import priv.zzz.model.TestUser;
import priv.zzz.result.Result;
import priv.zzz.result.ResultCode;
import priv.zzz.result.ResultSet;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@Api("测试接口")
public class ExampleController {

    // AOP log test
    @RequestMapping(value = "log", method = RequestMethod.GET)
    public String logTest(@RequestParam String name, @RequestParam int number) {
        return name + " " + number;
    }

    // Result<T> test
    @RequestMapping(value = "result", method = RequestMethod.GET)
    public Result<Integer> resultTest() {
        return Result.success(123);
    }

    // ResultSet<T> test
    @RequestMapping(value = "result/set", method = RequestMethod.GET)
    public ResultSet<TestUser> resultSetTest() {
        List<TestUser> userList = Arrays.asList(
                new TestUser("Alice", 20, 0, "12345@qq.com"),
                new TestUser("Eric", 21, 1, "12345@163.com"),
                new TestUser("France", 22, 1, "12345@gmail.com")
        );
        return ResultSet.success(userList.size(), userList);
    }

    // Result.failure test
    @RequestMapping(value = "fail", method = RequestMethod.GET)
    public Result<Integer> failTest(@RequestParam Integer code, HttpServletResponse response) {

        response.setStatus(code);
        if (code == 404) {
            return Result.failure(ResultCode.NOT_FOUND);
        }
        if (code == 403) {
            return Result.failure(ResultCode.UNAUTHORIZED);
        }
        if (code == 500) {
            return Result.failure(ResultCode.SERVER_ERROR);
        }
        return Result.failure();
    }

    // validator test
    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ApiOperation(value = "validator测试方法")
    public Result<Integer> saveUser(@RequestBody @Valid TestUser user) {
        // do something
        return Result.success();
    }

    // exception test
    @RequestMapping(value = "exception", method = RequestMethod.GET)
    public Result exampleException() throws ExampleException {
        throw new ExampleException("这是一个测试异常");
    }
}


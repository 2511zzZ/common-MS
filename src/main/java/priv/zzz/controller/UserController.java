package priv.zzz.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import priv.zzz.exception.ExampleException;
import priv.zzz.model.ExampleUser;
import priv.zzz.result.Result;
import priv.zzz.result.ResultSet;
import priv.zzz.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<Integer> saveUser(@RequestBody @Valid ExampleUser user) throws ExampleException {
        userService.saveUser(user);
        return Result.success();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<ExampleUser> getUser(@RequestParam String username) {
        return Result.success(userService.getUserByName(username));
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result<Integer> deleteUser(@RequestParam String username) throws ExampleException {
        userService.deleteUser(username);
        return Result.success();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result<ExampleUser> updateUser(@RequestBody @Valid ExampleUser user) throws ExampleException {
        userService.updateUser(user);
        return Result.success(userService.getUserByName(user.getUsername()));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultSet<ExampleUser> listUser() {
        List<ExampleUser> userList = userService.listUser();
        return ResultSet.success(userList.size(), userList);
    }

}

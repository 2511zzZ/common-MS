package priv.zzz.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import priv.zzz.result.Result;

@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam String username, @RequestParam String password) {

        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行登录方法
        subject.login(token);
        // 可能抛出的异常：UnknownAccountException  IncorrectCredentialsException
        if(subject.getSession() == null){
            return Result.failure(401, "未授权，请登录");
        }
        // 无异常则判断为登录成功
        String sessionId = (String) subject.getSession().getId();
        return Result.success(sessionId);
    }

    @RequestMapping(value = "/401", method = RequestMethod.GET)
    public Result unAuth() {
        return Result.failure(401, "未授权，请登录");
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public Result forbidden() {
        return Result.failure(403, "权限不足");
    }
}

package priv.zzz.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import priv.zzz.exception.ExampleException;
import priv.zzz.result.Result;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
@RestController
public class ExceptionController {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public Result<String> validatorException(HttpServletResponse response, MethodArgumentNotValidException e) {
        // validator设置了message时返回message，未设置则返回“非法参数”
        FieldError error = e.getBindingResult().getFieldError();
        String message = "非法参数";
        if(error != null){
            message = error.getField() + error.getDefaultMessage();
        }
        response.setStatus(400);
        return Result.failure(400, message);
    }

    @ExceptionHandler(value = { IllegalAccessException.class })
    public Result assertException(IllegalAccessException e) {
        return Result.failure(400, e.getMessage());
    }

    @ExceptionHandler(value = { IncorrectCredentialsException.class, UnknownAccountException.class })
    public Result shiroException() {
        return Result.failure(401, "用户名或密码错误，请重试");
    }

    @ExceptionHandler(value = {ExampleException.class})
    public Result exampleException(ExampleException e){
        return Result.failure(-1, e.getMessage());
    }

}

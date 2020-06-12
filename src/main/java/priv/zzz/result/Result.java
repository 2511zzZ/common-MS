package priv.zzz.result;

import lombok.Data;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

@Data
// 返回单个数据或对象
public class Result<T> implements Serializable {

    Date timestamp;
    Integer status;
    String message;
    T data;
    String path;

    public Result() {}

    public Result(Integer status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = new Date();
        this.path = "";

        // 使用RequestContextHolder获取请求路径
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if(attributes != null){
            HttpServletRequest request = ((ServletRequestAttributes)attributes).getRequest();
            this.path = request.getRequestURI();
        }
    }

    public static <T> Result<T> success(){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(Integer status, String message, T data){
        return new Result<>(status, message, data);
    }

    public static <T> Result<T> failure(){
        return new Result<>(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage(), null);
    }

    public static <T> Result<T> failure(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static <T> Result<T> failure(Integer status, String message){
        return new Result<>(status, message, null);
    }
}

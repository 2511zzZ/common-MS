package priv.zzz.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    Integer status;
    String message;
    T data;

    public Result() {}

    public Result(Integer status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
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

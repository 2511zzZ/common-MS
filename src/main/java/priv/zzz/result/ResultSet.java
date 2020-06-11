package priv.zzz.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultSet<T> implements Serializable {

    int total;
    Integer status;
    String message;
    List<T> list;

    public ResultSet() {}

    public ResultSet(int total, Integer status, String message ,List<T> list){
        this.total = total;
        this.status = status;
        this.message = message;
        this.list = list;
    }

    public static <T> ResultSet<T> success(){
        return new ResultSet<>(0, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> ResultSet<T> success(int total, List<T> list){
        return new ResultSet<>(total, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), list);
    }

    public static <T> ResultSet<T> success(int total, Integer status, String message, List<T> list){
        return new ResultSet<>(total, status, message, list);
    }

    public static <T> ResultSet<T> failure(){
        return new ResultSet<>(0, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage(), null);
    }

    public static <T> ResultSet<T> failure(Integer status, String message){
        return new ResultSet<>(0, status, message, null);
    }
}

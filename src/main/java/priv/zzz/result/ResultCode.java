package priv.zzz.result;

public enum ResultCode {

    SUCCESS(200, "success"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "not found"),
    SERVER_ERROR(500, "internal server error"),

    FAIL(-1, "failure")
    ;

    private int code;
    private String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

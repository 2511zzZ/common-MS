package priv.zzz.shiro;

public class ShiroConstant {

    private ShiroConstant() {}

    /**
     * 使用sessionId作为shiro认证依据
     */
    public final static String SESSION_ID_NAME = "sessionId";

    /**
     * 使用JWT作为shiro认证依据
     */
    public final static String JWT_NAME = "JWT";

}

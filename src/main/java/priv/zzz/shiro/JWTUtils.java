package priv.zzz.shiro;

// 使用shiro自带的sessionId和使用JWT进行身份验证的区别是什么?
// 两者都是发送登录请求后返回字符串
// shiro默认使用sessionId并保存在cookie中
// 用户后续请求都会通过这个sessionId去获取包含用户信息的session实体
// session实体是保存在服务器的内存中的
// 而JWT本身就包含有用户信息,因此服务器不需要存储用户身份相关数据

public class JWTUtils {

    public String generateJWT () {

        return null;
    }

    public String parseJWT () {

        return null;
    }

}

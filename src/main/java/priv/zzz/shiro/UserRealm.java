package priv.zzz.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import priv.zzz.model.ExampleUser;
import priv.zzz.service.UserService;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    @Override
    //执行授权逻辑
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        ExampleUser user = (ExampleUser)principals.getPrimaryPrincipal();
        // 根据用户身份授权
        // 一般通过role等属性进行，这里仅作为演示
        if(user.getUsername().equals("zzz")){
            info.addRole("user:list");
        }
        return info;
    }

    @Override
    //执行认证逻辑
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token1) {

        UsernamePasswordToken token = (UsernamePasswordToken)token1;
        ExampleUser user = userService.getUserByName(token.getUsername());

        if (user == null) { return null; }

        //这里的实际密码应该去数据库取，即user.getPassword(),shiro会将token的密码与实际密码做对比
        // 我的数据库尚未添加password字段，这里统一使用 123456 作为密码
        String realityPassword = "123456";
        return new SimpleAuthenticationInfo(user, realityPassword, "");
    }
}

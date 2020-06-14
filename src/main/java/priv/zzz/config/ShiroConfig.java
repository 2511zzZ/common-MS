package priv.zzz.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import priv.zzz.shiro.ShiroFilterMap;
import priv.zzz.shiro.UserRealm;

import java.util.Map;


@Configuration
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // filterMap设置过滤规则
        Map<String, String> filterMap = ShiroFilterMap.getInstance();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 设置认证失败的重定向路由
        shiroFilterFactoryBean.setLoginUrl("/401");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        return shiroFilterFactoryBean;
    }

    //创建DefaultWebSecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    // 创建Realm
    @Bean(name="userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

}

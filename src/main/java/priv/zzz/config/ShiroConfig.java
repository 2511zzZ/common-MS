package priv.zzz.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import priv.zzz.shiro.*;

import java.util.Map;


@Configuration
public class ShiroConfig {

    private final static String sessionStrategy = "sessionId";
//    private static String sessionStrategy = "JWT";

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
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm,
                                                                  @Qualifier("mySessionManager") MySessionManager mySessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);

        if(sessionStrategy.equals(ShiroConstant.SESSION_ID_NAME)){
            // 使用tokenManager直接返回sessionId
        // 禁止重写URL 否则会出现/401;JSESSIONID=XXX之类的情况
        mySessionManager.setSessionIdUrlRewritingEnabled(false);
        //设置sessionManager
        securityManager.setSessionManager(mySessionManager);
        }else if (sessionStrategy.equals(ShiroConstant.JWT_NAME)){
            // 使用cacheManager返回JWT
//            DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//            DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//            // 关闭shiro的默认session
//            defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//            subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//            securityManager.setSubjectDAO(subjectDAO);
//            // 使用自己的cacheManager
//            securityManager.setCacheManager(new MyCacheManager());
        }


        return securityManager;
    }

    // 创建Realm
    @Bean(name="userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    @Bean(name="mySessionManager")
    public MySessionManager getMySessionManager(){
        return new MySessionManager();
    }

}

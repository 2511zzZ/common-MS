package priv.zzz.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShiroFilterMap {

    private static volatile Map<String, String> filterMap;

    private ShiroFilterMap() {}

    public static Map<String, String> getInstance() {
        if (filterMap == null){
            synchronized (ShiroFilterMap.class){
                if (filterMap == null){
                    filterMap = new LinkedHashMap<>();

                    //添加filter
                    /*
                     * 常用过滤器：
                     * anno:无需认证
                     * authc:需要认证
                     * user:使用RememberMe功能可以直接访问
                     * perms:该资源必须得到资源权限才可以访问
                     * roles：该资源必须得到角色权限才可以访问
                     */

                    // 开发或测试环境下 由于swagger2需要传输很多名称无规律文件
                    // 因此这里对需要进行权限过滤的路由进行设置
                    // 不设置的一律为不需要认证即可访问
                    filterMap.put("/user/list", "roles[user:list]");
                    filterMap.put("/user/**", "authc");
                    // 其他...

                    // 开放完成后如果不需要swagger2
                    // 可以只配置不需要认证的路由，对其他路由一律拦截，如：
                    // filterMap.put("/login", "anon");
                    // filterMap.put("/**", "authc");

                }
            }
        }
        return filterMap;
    }

}

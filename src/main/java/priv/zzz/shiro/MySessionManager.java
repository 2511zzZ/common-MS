package priv.zzz.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;


public class MySessionManager extends DefaultWebSessionManager {

    public MySessionManager() {
        super();
    }

    /**
     * 如果请求头中带有sessionId,就返回该sessionId
     * 由于swagger2目前似乎不支持在页面上添加请求头，因此这里还提供默认的从cookie中获取sessionId的方式
     * 想要模拟前后端分离情况、从请求头中获取sessionId的话可以使用PostMan等工具
     * 实际应用中前端工程login之后将返回的结果添加到header中即可：{"sessionId":"xxxxxxxxx"}
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        String id = WebUtils.toHttp(request).getHeader(ShiroConstant.SESSION_ID_NAME);

        if (!StringUtils.isEmpty(id)) { return id; }

        return super.getSessionId(request, response);
    }


}
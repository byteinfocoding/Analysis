package top.byteinfo.mallsecurity.handel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 动态权限数据源，用于获取动态权限规则
 * Created by macro on 2020/2/7.
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, ConfigAttribute> configAttributeMap = null;
    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (configAttributeMap == null) this.loadDataSource();
        List<ConfigAttribute>  configAttributes = new ArrayList<>();
        //获取当前访问的路径
        /**
         * Method              URL-Decoded Result
         * ----------------------------------------------------
         * getContextPath()        no      /app
         * getLocalAddr()                  127.0.0.1
         * getLocalName()                  30thh.loc
         * getLocalPort()                  8480
         * getMethod()                     GET
         * getPathInfo()           yes     /a?+b
         * getProtocol()                   HTTP/1.1
         * getQueryString()        no      p+1=c+d&p+2=e+f
         * getRequestedSessionId() no      S%3F+ID
         * getRequestURI()         no      /app/test%3F/a%3F+b;jsessionid=S+ID
         * getRequestURL()         no      http://30thh.loc:8480/app/test%3F/a%3F+b;jsessionid=S+ID
         * getScheme()                     http
         * getServerName()                 30thh.loc
         * getServerPort()                 8480
         * getServletPath()        yes     /test?
         * getParameterNames()     yes     [p 2, p 1]
         * getParameter("p 1")     yes     c d
         */
        String url = ((FilterInvocation) o).getRequestUrl();

        String path = ((FilterInvocation) o).getHttpRequest().getRequestURI();


        /**
         * URLUtil.getPath(url)
         * 	public static String getPath(String uriStr) {
         * 		URI uri;
         * 		try {
         * 			uri = new URI(uriStr);
         *                } catch (URISyntaxException e) {
         * 			throw new UtilException(e);
         *        }
         * 		return uri.getPath();* 	}
          */
//        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = configAttributeMap.keySet().iterator();
        //获取访问该路径所需资源
        while (iterator.hasNext()) {
            String pattern = iterator.next();
            if (pathMatcher.match(pattern, path)) {
                configAttributes.add(configAttributeMap.get(pattern));
            }
        }
        // 未设置操作请求权限，返回空集合
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}

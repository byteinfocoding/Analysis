package top.byteinfo.springsecurity.handler;

import lombok.SneakyThrows;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 接口拦截规则
 *
 * @author yezhiqiu
 * @date 2021/07/27
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

//    /**
//     * 资源角色列表
//     */
//    private static List<ResourceRoleDTO> resourceRoleList;
//
//    @Autowired
//    private RoleDao roleDao;
//
//    /**
//     * 加载资源角色信息
//     */
//    @PostConstruct
//    private void loadDataSource() {
//        resourceRoleList = roleDao.listResourceRoles();
//    }
//
//    /**
//     * 清空接口角色信息
//     */
//    public void clearDataSource() {
//        resourceRoleList = null;
//    }

    @SneakyThrows
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        // 修改接口角色关系后重新加载
//        if (CollectionUtils.isEmpty(resourceRoleList)) {
//            this.loadDataSource();
//        }
        FilterInvocation fi = (FilterInvocation) object;
        // 获取用户请求方式
        String method = fi.getRequest().getMethod();
//        System.out.println(method);
        // 获取用户请求Url
        String url = fi.getRequest().getRequestURI();
//        System.out.println(url);
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 获取接口角色信息，若为匿名接口则放行，若无对应角色则禁止


//        roleList.add("user");
//        roleList.add("test");


        if (antPathMatcher.match("/admin", url) && (method.equals("GET"))) {
            List<String> roleList = new ArrayList<>();
            roleList.add("admin");
            return SecurityConfig.createList(roleList.toArray(new String[]{}));
        } else if (antPathMatcher.match("/test", url) && (method.equals("GET"))) {
            List<String> roleList = new ArrayList<>();
            roleList.add("test");
            return SecurityConfig.createList(roleList.toArray(new String[]{}));
        }

//        for (ResourceRoleDTO resourceRoleDTO : resourceRoleList) {
//            if (antPathMatcher.match(resourceRoleDTO.getUrl(), url) && resourceRoleDTO.getRequestMethod().equals(method)) {
//                List<String> roleList = resourceRoleDTO.getRoleList();
//                //该if判断似乎无效 TODO
//                // fixme
//                if (CollectionUtils.isEmpty(roleList)) {
//                    return SecurityConfig.createList("disable");
//                }
//                return SecurityConfig.createList(roleList.toArray(new String[]{}));
//            }
//        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}

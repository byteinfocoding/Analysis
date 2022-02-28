package top.byteinfo.mallsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.byteinfo.mallsecurity.dao.UmsAdminMapper;
import top.byteinfo.mallsecurity.dao.UmsResourceMapper;
import top.byteinfo.mallsecurity.entity.UmsAdmin;
import top.byteinfo.mallsecurity.entity.UmsResource;

import java.util.List;
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsResourceMapper resourceMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UmsAdmin> adminList = adminMapper.selectByPrimaryName(username);
        UmsAdmin admin = adminList.get(0);
        if (admin != null) {
            List<UmsResource> resourceList  = resourceMapper.getResourceList(admin.getId());
//            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
//        return null;
    }
}

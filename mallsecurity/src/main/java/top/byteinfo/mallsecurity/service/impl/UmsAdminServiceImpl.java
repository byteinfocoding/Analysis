package top.byteinfo.mallsecurity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.byteinfo.mallsecurity.config.AdminUserDetails;
import top.byteinfo.mallsecurity.dao.UmsAdminMapper;
import top.byteinfo.mallsecurity.dao.UmsResourceMapper;
import top.byteinfo.mallsecurity.entity.UmsAdmin;
import top.byteinfo.mallsecurity.entity.UmsResource;
import top.byteinfo.mallsecurity.model.JwtTokenUtil;
import top.byteinfo.mallsecurity.service.UmsAdminService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台用户管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public String login(String username, String password) {
//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(username);

        List<UmsAdmin> adminList = adminMapper.selectByPrimaryName(username);
        UmsAdmin admin = adminList.get(0);
        List<UmsResource> resourceList = resourceMapper.getResourceList(admin.getId());
        UserDetails userDetails = new AdminUserDetails(admin, resourceList);
        String token = null;
        //密码需要客户端加密后传递
        try {
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new RuntimeException("密码不正确");
            }
            if (!userDetails.isEnabled()) {
                throw new RuntimeException("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);

        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


//    @Override
//    public UserDetails loadUserByUsername(String username){
////        //获取用户信息
////        UmsAdminExample example = new UmsAdminExample();
////        example.createCriteria().andUsernameEqualTo(username);
//        List<UmsAdmin> adminList = adminMapper.selectByPrimaryName(username);
//        UmsAdmin admin = adminList.get(0);
//        if (admin != null) {
//            List<UmsResource> resourceList  = resourceMapper.getResourceList(admin.getId());
////            List<UmsResource> resourceList = getResourceList(admin.getId());
//            return new AdminUserDetails(admin,resourceList);
//        }
//        throw new UsernameNotFoundException("用户名或密码错误");
//    }

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectAll();
    }
}

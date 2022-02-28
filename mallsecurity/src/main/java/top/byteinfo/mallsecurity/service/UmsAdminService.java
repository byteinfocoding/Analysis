package top.byteinfo.mallsecurity.service;

import top.byteinfo.mallsecurity.entity.UmsResource;

import java.util.List;

/**
 * 后台用户管理Service
 * Created by macro on 2018/4/26.
 */
public interface UmsAdminService {
    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);
    /**
     * 获取用户信息
     */
//    UserDetails loadUserByUsername(String username);

    /**
     * 查询全部资源
     */
    List<UmsResource> listAll();
}

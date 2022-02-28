package top.byteinfo.springsecurity.handler;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import top.byteinfo.springsecurity.model.JSON;
import top.byteinfo.springsecurity.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录成功处理
 *
 * @author yezhiqiu
 * @date 2021/07/28
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
//    @Autowired
//    private UserAuthDao userAuthDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // 返回登录信息
//        UserInfoDTO userLoginDTO = BeanCopyUtils.copyObject(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), UserInfoDTO.class);
//        UserInfoDTO userLoginDTO = BeanCopyUtils.copyObject(UserUtils.getLoginUser(), UserInfoDTO.class);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.getWriter().write(JSON.toJSONString(Result.ok(userLoginDTO)));
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal())));
//        // 更新用户ip，最近登录时间
//        updateUserInfo();
    }

    /**
     * 更新用户信息
     */
//    @Async
//    public void updateUserInfo() {
//        UserAuth userAuth = UserAuth.builder()
//                .id(UserUtils.getLoginUser().getId())
//                .ipAddress(UserUtils.getLoginUser().getIpAddress())
//                .ipSource(UserUtils.getLoginUser().getIpSource())
//                .lastLoginTime(UserUtils.getLoginUser().getLastLoginTime())
//                .build();
//        //TODO
////        userAuthDao.updateById(userAuth);
//    }

}

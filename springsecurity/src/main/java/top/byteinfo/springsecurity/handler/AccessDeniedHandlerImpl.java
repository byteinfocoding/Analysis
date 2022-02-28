package top.byteinfo.springsecurity.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import top.byteinfo.springsecurity.model.JSON;
import top.byteinfo.springsecurity.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户权限处理
 *
 * @author yezhiqiu
 * @date 2021/07/28
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
//        String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(Result.fail("权限不足"));
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.fail("权限不足")));
//        httpServletResponse.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(Result.fail("权限不足")));
    }

}

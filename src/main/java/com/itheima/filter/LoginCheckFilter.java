package com.itheima.filter;


import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 转化为 HttpServletRequest
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        //  1.获取请求url。
        String requestURL = httpRequest.getRequestURL().toString();
        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if (requestURL.contains("login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;

        }
        //3.获取请求头中的令牌（token）。
        String token = httpRequest.getHeader("token");

        if (!StringUtils.hasLength(token)){
            log.info("请求头token为空,返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String jsonString = JSONObject.toJSONString(error);
            // 输出流
            PrintWriter writer = httpResponse.getWriter();
            writer.write(jsonString);
            return;

        }


        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            httpResponse.getWriter().write(notLogin);
            return;
        }
        log.info("登录成功");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

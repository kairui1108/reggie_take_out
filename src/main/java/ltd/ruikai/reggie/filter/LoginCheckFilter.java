package ltd.ruikai.reggie.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import ltd.ruikai.reggie.common.BaseContext;
import ltd.ruikai.reggie.common.R;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ author  tanruikai
 * @ date  2022/9/3 21:24
 * @ version 1.0
 * 检查用户是否登录
 */

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/favicon.ico",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

        boolean check = check(urls, requestURI);
        if(check){
            filterChain.doFilter(request, response);
            return;
        }

        if(request.getSession().getAttribute("employee") != null){
            Long ID = (Long)request.getSession().getAttribute("employee");
            log.info("用户{}已登录", ID);
            BaseContext.setCurrenId(ID);
            filterChain.doFilter(request, response);
            return;
        }

        if(request.getSession().getAttribute("user") != null){
            Long userID = (Long)request.getSession().getAttribute("user");
            log.info("移动端用户{}已登录", userID);
            BaseContext.setCurrenId(userID);
            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登录，拦截到请求： {}", request.getRequestURI());
        response.getWriter().write(JSON.toJSONString(R.error("NotLogin")));

    }

    /**
     * 判断是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match) {
                return true;
            }
        }
        return false;
    }
}

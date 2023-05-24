package com.javays.base.web.filter;

import com.javays.base.util.ServletUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理跨域访问
 */
@WebFilter(filterName = "CorsFilter", urlPatterns = "*.do")
public class CorsFilter implements Filter {

    public CorsFilter() {
        System.out.println("tomcat启动时调用，实例化CorsFilter类");
    }

    public void init(FilterConfig config) throws ServletException {
        String filterName = config.getFilterName();
        System.out.println("tomcat启动时调用，初始化Filter中的数据，过滤器类的别名：" + filterName);
    }

    public void destroy() {
        System.out.println("tomcat停止时调用，销毁");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("访问到 当前filter 中配置 urlPatterns = ‘*.do’ 同类型的访问地址，由tomcat调用多次 ");
        // 转换类型
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 跨域访问
        ServletUtils.processCors(httpServletResponse);
        // 放行
        chain.doFilter(request, httpServletResponse);
    }
}

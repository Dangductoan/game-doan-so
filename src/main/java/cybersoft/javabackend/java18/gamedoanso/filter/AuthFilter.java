package cybersoft.javabackend.java18.gamedoanso.filter;

import cybersoft.javabackend.java18.gamedoanso.utils.UrlUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = UrlUtils.ALL)
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if(isAuthUrl(req)){
            filterChain.doFilter(req, resp);
        } else
            if(req.getSession().getAttribute("currentUser") != null)
                filterChain.doFilter(req, resp);
            else
                resp.sendRedirect(req.getContextPath() + UrlUtils.DANG_NHAP);
    }

    private boolean isAuthUrl(HttpServletRequest request){
        return request.getServletPath().startsWith(UrlUtils.DANG_KY)
                || request.getServletPath().startsWith(UrlUtils.DANG_NHAP);
    }
}

package me.tobiadeyinka.movies.config;

import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilterConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // required method definition
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Allow-Headers",
                "Origin," +
                        "Accept," +
                        "X-Requested-With," +
                        "Content-Type," +
                        "Access-Control-Request-Method," +
                        "Access-Control-Request-Headers," +
                        "Authorization," +
                        "clientId," +
                        "authToken," +
                        "userId");

        if(request.getMethod().equals(HttpMethod.OPTIONS.name())) response.setStatus(HttpStatus.NO_CONTENT.value());
        else chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // required method definition
    }

}

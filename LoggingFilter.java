package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();
        String remoteAddr = httpRequest.getRemoteAddr();
        Date currentTime = new Date();

        String logMessage = String.format(
                "%s | IP: %s | URL: %s",
                dateFormat.format(currentTime),
                remoteAddr,
                requestURI
        );

        System.out.println(logMessage);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

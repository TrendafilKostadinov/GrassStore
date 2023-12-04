package bg.softuni.grassstore.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class IPCheckInterceptor implements HandlerInterceptor {

    private static final String BLOCKED_IP = "127.0.0.1";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String clientIP = getClientIP(request);

        if (BLOCKED_IP.equals(clientIP)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for this IP address.");
            return false;
        }

        return true;
    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }


}

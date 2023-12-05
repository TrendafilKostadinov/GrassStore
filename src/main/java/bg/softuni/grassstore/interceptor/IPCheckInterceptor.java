package bg.softuni.grassstore.interceptor;


import bg.softuni.grassstore.service.BlockedIpService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

public class IPCheckInterceptor implements HandlerInterceptor {

    private final BlockedIpService blockedIpService;

    public IPCheckInterceptor(BlockedIpService blockedIpService) {
        this.blockedIpService = blockedIpService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String clientIP = getClientIP(request);
        List<String> blockedIps = blockedIpService.getBlockedIps();

        for (String blockedIp : blockedIps) {
            if (blockedIp.equals(clientIP)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for this IP address.");
                return false;
            }
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

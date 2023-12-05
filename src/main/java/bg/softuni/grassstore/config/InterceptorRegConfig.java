package bg.softuni.grassstore.config;

import bg.softuni.grassstore.interceptor.IPCheckInterceptor;
import bg.softuni.grassstore.service.BlockedIpService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorRegConfig implements WebMvcConfigurer {

    private final BlockedIpService blockedIpService;

    public InterceptorRegConfig(BlockedIpService blockedIpService) {
        this.blockedIpService = blockedIpService;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IPCheckInterceptor(blockedIpService))
                .addPathPatterns("/login")
                .addPathPatterns("/")
                .addPathPatterns("/home");
    }
}

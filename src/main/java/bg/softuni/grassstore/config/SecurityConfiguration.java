package bg.softuni.grassstore.config;

import bg.softuni.grassstore.model.enums.RoleNames;
import bg.softuni.grassstore.repository.UserRepository;
import bg.softuni.grassstore.service.GGUserDetailService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/login", "/login-error", "/").permitAll()
                                .requestMatchers("/error").permitAll()
                                .requestMatchers(
                                        "/user-add",
                                        "/admin/user-password/**",
                                        "/user-delete/**",
                                        "/user-detail/**").hasRole(RoleNames.ADMIN.name())
                                .requestMatchers(
                                        "/delivery-add/**",
                                        "/product-add",
                                        "/order-ship/**").hasRole(RoleNames.OSB.name())
                                .requestMatchers(
                                        "/address-add",
                                        "/customer-add",
                                        "/order-add/**").hasRole(RoleNames.TRADER.name())
                                .requestMatchers("/assign-role").hasRole(RoleNames.MANAGER.name())
                                .anyRequest().authenticated()
                ).formLogin(
                        formLogin -> {
                            formLogin
                                    .loginPage("/login")
                                    .usernameParameter("email")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/home")
                                    .failureForwardUrl("/login-error");
                        }
                ).logout(
                        logout -> {
                            logout
                                    .logoutUrl("/logout")
                                    .logoutSuccessUrl("/login")
                                    .invalidateHttpSession(true);
                        }
                ).sessionManagement()
                .maximumSessions(1).sessionRegistry(sessionRegistry());

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new GGUserDetailService(userRepository);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}

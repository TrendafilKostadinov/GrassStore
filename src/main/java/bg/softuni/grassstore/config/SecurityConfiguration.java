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
        return httpSecurity.authorizeHttpRequests(
                // Define which urls are visible by which users
                authorizeRequests -> authorizeRequests
                        // All static resources which are situated in js, images, css are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Allow anyone to see the home page, the registration page and the login form
                        .requestMatchers("/login", "/login-error", "/").permitAll()
//                        .requestMatchers("/offers/all").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/offer/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/user-add").hasRole(RoleNames.ADMIN.name())
                        .requestMatchers("/assign-role").hasRole(RoleNames.MANAGER.name())
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> {
                    formLogin
                            // redirect here when we access something which is not allowed.
                            // also this is the page where we perform login.
                            .loginPage("/login")
                            // The names of the input fields (in our case in login.html)
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/home")
                            .failureForwardUrl("/login-error");
                }
        ).logout(
                logout -> {
                    logout
                            // the URL where we should POST something in order to perform the logout
                            .logoutUrl("/logout")
                            // where to go when logged out?
                            .logoutSuccessUrl("/login")
                            // invalidate the HTTP session
                            .invalidateHttpSession(true);
                }
        ).build();
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

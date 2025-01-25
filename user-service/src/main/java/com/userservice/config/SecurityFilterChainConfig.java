package com.userservice.config;

import com.userservice.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterChainConfig {

    private final List<String> permittedRoutes = List.of(
            "/api/user/register",
            "/api/user/login"
    );

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<RequestMatcher> matchers = permittedRoutes.stream()
                .map(AntPathRequestMatcher::new)
                .collect(Collectors.toList());
        RequestMatcher permittedRoutesMatcher = new OrRequestMatcher(matchers);

        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(customizer ->
                    customizer
                            .requestMatchers(permittedRoutesMatcher)
                            .permitAll()
                            .anyRequest()
                            .authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

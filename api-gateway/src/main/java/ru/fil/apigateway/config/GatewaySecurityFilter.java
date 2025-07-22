package ru.fil.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class GatewaySecurityFilter {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authorizeExchange(authExchange -> authExchange
                        .pathMatchers("/auth/login", "/auth/register").permitAll()
                        .pathMatchers("/user/forApp", "/user/all/forApp", "/user/admin/forApp").hasRole("ADMIN")
                        .pathMatchers("/address/streets/add", "/address/streets/del/{id}",
                                "/address/houses/add", "/address/houses/del/{id}",
                                "/address/apartments/add", "/address/apartments/forApp").hasRole("ADMIN")
                        .pathMatchers("/address/**").permitAll()
                        .pathMatchers("/application/update/{id}/{status}", "/application/all").hasRole("ADMIN")
                        .pathMatchers("/notification/**").hasRole("ADMIN")
                        .anyExchange().authenticated())
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }
}

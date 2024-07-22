package com.payriff.dictionary_ms.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    private final JwtService jwtService;
//
//    private final UserDetailsService userDetailsService;
//
//    public SecurityConfig(JwtService jwtService, UserDetailsService userDetailsService) {
//        this.jwtService = jwtService;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter(jwtService, userDetailsService);
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(this::customizeCSRF)
//        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/auth/user/me/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/auth/user/**").hasRole("ADMIN")
                        .requestMatchers("/api/auth/register", "/api/auth/token", "/api/auth/validate", "/api/auth/login").permitAll()
                )

//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .authenticationEntryPoint((request, response, authException) ->
//                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
//                        )
//                        .accessDeniedHandler((request, response, accessDeniedException) ->
//                                response.setStatus(HttpServletResponse.SC_FORBIDDEN)
//                        )
//                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }

    private void customizeCSRF(CsrfConfigurer<HttpSecurity> csrfConfigurer) {
        csrfConfigurer.ignoringRequestMatchers("/**");
    }
}
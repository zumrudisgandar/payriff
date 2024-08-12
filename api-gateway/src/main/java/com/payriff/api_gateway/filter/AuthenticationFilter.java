package com.payriff.api_gateway.filter;

import com.payriff.api_gateway.util.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private  RouteValidator validator;

    @Autowired
    private RestTemplate template;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);

    }

    @Override
    public GatewayFilter apply(Config config) {

        System.out.println("MANUAL1: entered gateway filter!");

        return ((exchange, chain) -> {

            if (exchange.getRequest().getURI().getPath().contains("/api/v2")) {
                return chain.filter(exchange);
            }

            if (validator.isSecured.test(exchange.getRequest())) {
                System.out.println("MANUAL2: entered next step after gateway filter!");
                if (!exchange.getRequest().getHeaders()
                        .containsKey(HttpHeaders.AUTHORIZATION)) {
                    System.out.println("MANUAL3: Missing Authorization header");
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                    System.out.println("MANUAL4: Authorization header" + authHeader);
                }
                try {
                    System.out.println("MANUAL5: Validating token with security-ms: {}" + authHeader);
                    template.getForObject("http://localhost:9898/api/auth/validate?token=" + authHeader, String.class);
                    jwtUtil.validateToken(authHeader);
                    List<String> roles = jwtUtil.extractRoles(authHeader);
                    String userId = jwtUtil.extractUserId(authHeader);
                    String userEmail = jwtUtil.extractUserEmail(authHeader);


                    if (userId!=null && userEmail!=null){
                        System.out.println("MANUAL6: userId: " + userId + "userEmail" + userEmail);
                        ServerHttpRequest request = exchange.getRequest().mutate()
                                .header("X-User-Id", userId)
                                .header("X-User-Email",userEmail)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authHeader)
                                .build();
                        exchange = exchange.mutate().request(request).build();
                    }


                    if (roles == null || !roles.contains(config.getRole())) {
                        System.out.println("MANUAL7: Unauthorized access attempt with roles: {}" + roles);
                        throw new RuntimeException("un authorized access to application");
                    }

                    // Forward the request with the Authorization header to payment-ms
                    ServerHttpRequest updatedRequest = exchange.getRequest().mutate()
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + authHeader)
                            .build();
                    exchange = exchange.mutate().request(updatedRequest).build();

                } catch (Exception e) {
                    System.out.println("MANUAL7: Invalid access: {}" + e.getMessage());
                    throw new RuntimeException("Unauthorized request!");
                }
            }
            return chain.filter(exchange);
        });
    }

    @Getter
    @Setter
    public static class Config {
        private String role;
    }
}
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
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {

                if (!exchange.getRequest().getHeaders()
                        .containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {

                    template.getForObject("http://localhost:8085/auth/validate?token=" + authHeader, String.class);
                    jwtUtil.validateToken(authHeader);
                    List<String> roles = jwtUtil.extractRoles(authHeader);
                    String userId = jwtUtil.extractUserId(authHeader);
                    String userEmail = jwtUtil.extractUserEmail(authHeader);


                    if (userId!=null && userEmail!=null){
                        ServerHttpRequest request = exchange.getRequest().mutate()
                                .header("X-User-Id", userId).header("X-User-Email",userEmail)
                                .build();
                        exchange = exchange.mutate().request(request).build();
                    }


                    if (roles == null || !roles.contains(config.getRole())) {
                        throw new RuntimeException("un authorized access to application");
                    }

                } catch (Exception e) {
                    System.out.println("invalid access...!");
//                    throw new UnAuthException("Unauthorized request!");
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

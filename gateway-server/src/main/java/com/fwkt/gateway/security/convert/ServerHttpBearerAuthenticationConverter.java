package com.fwkt.gateway.security.convert;

import com.fwkt.gateway.jwt.AuthorizationHeaderPayload;
import com.fwkt.gateway.jwt.JWTCustomVerifier;
import com.fwkt.gateway.jwt.UsernamePasswordAuthenticationBearer;
import com.fwkt.gateway.jwt.exception.JwtExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-13 15:30
 */
public class ServerHttpBearerAuthenticationConverter implements ServerAuthenticationConverter {
    private static final String BEARER = "Bearer ";
    private static final Predicate<String> matchBearerLength = authValue -> authValue.length() > BEARER.length();
    private static final Function<String, Mono<String>> isolateBearerValue = authValue -> Mono.justOrEmpty(authValue.substring(BEARER.length()));

    private JWTCustomVerifier jwtVerifier = new JWTCustomVerifier();

    /**
     * Apply this function to the current WebExchange, an Authentication object
     * is returned when completed.
     *
     * @param serverWebExchange
     * @return
     */
    @Override
    public Mono<Authentication> convert(ServerWebExchange serverWebExchange) {
        System.out.println("===ServerHttpBearerAuthenticationConverter=进入convert匹配====");

        try{
            return Mono.justOrEmpty(serverWebExchange)
                    .flatMap(AuthorizationHeaderPayload::extract)
                    .filter(matchBearerLength)
                    .flatMap(isolateBearerValue)
                    .flatMap(jwtVerifier::check)
                    .flatMap(UsernamePasswordAuthenticationBearer::create).log();
        }catch (Exception e){
            throw new JwtExpiredException("11");
        }
    }
}

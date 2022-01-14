package com.fwkt.gateway.security.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

public class UserGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI uri = exchange.getRequest().getURI();
        String originalQuery = uri.getRawQuery();
        StringBuilder query = new StringBuilder();

        if (StringUtils.hasText(originalQuery)) {
            query.append(originalQuery);
            if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                query.append('&');
            }
        }
        //获取用户信息
        query.append("clientip");
        query.append("=");
        query.append(getIPAddr(exchange.getRequest()));

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(
                        authentication -> {
                            //System.out.println("进入用户过滤器" + JSON.toJSONString(authentication));
                            //获取用户信息
                            query.append('&');
                            query.append("userid");
                            query.append("=");
                            query.append(authentication.getName());
                            return chain.filter(exchange.mutate().request(exchange.getRequest().mutate().uri(UriComponentsBuilder.fromUri(uri)
                                    .replaceQuery(query.toString())
                                    .build(true)
                                    .toUri()).build()).build());
                        }
                )
                .switchIfEmpty(chain.filter(exchange.mutate()
                        .request(exchange.getRequest().mutate().uri(UriComponentsBuilder.fromUri(uri)
                                .replaceQuery(query.toString())
                                .build(true)
                                .toUri()).build())
                        .build()));
    }

    @Override
    public int getOrder() {
        return -100;
    }


    private String getIPAddr(ServerHttpRequest request) {
        String ip = request.getHeaders().getFirst("X-CLIENT-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress().getAddress().getHostAddress();
        }
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}

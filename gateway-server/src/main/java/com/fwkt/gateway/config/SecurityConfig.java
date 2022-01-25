package com.fwkt.gateway.config;

import com.fwkt.gateway.manager.AdminLoginAuthenticationManager;
import com.fwkt.gateway.manager.BearerTokenReactiveAuthenticationManager;
import com.fwkt.gateway.manager.LoginAuthenticationManager;
import com.fwkt.gateway.security.convert.ServerHttpBearerAuthenticationConverter;
import com.fwkt.gateway.security.handler.CustomHttpBasicServerAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

import java.util.LinkedList;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-13 13:57
 */
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private CustomHttpBasicServerAuthenticationEntryPoint customHttpBasicServerAuthenticationEntryPoint;

    //security的鉴权排除列表
    private static final String[] excludedAuthPages = {
            "/test/**",
            "/*/test/**",
            "/auth/**",
            "/*/auth/**"


    };

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
                .pathMatchers(excludedAuthPages).permitAll()  //无需进行权限过滤的请求路径
                .pathMatchers(HttpMethod.OPTIONS).permitAll() //option 请求默认放行

                .and()
                .authorizeExchange()
                .anyExchange().authenticated()
                .and().formLogin().disable()
                .exceptionHandling().authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint)  //基于http的接口请求鉴权失败
                .and().csrf().disable()//必须支持跨域
                .logout().disable().authorizeExchange()
                .and().addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);


//                .anyExchange().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin() //启动页面表单登陆,spring security 内置了一个登陆页面/login
//                .and().csrf().disable()//必须支持跨域
//                .logout().disable();

        return http.build();


    }

    private AuthenticationWebFilter bearerAuthenticationFilter() {
        ReactiveAuthenticationManager authManager = new BearerTokenReactiveAuthenticationManager();
        AuthenticationWebFilter bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
        bearerAuthenticationFilter.setServerAuthenticationConverter(new ServerHttpBearerAuthenticationConverter());
        //bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));
        return bearerAuthenticationFilter;
    }

    /**
     * 默认注入到reactiveAuthenticationManager
     *
     * @return
     */
    @Bean
    @Primary
    ReactiveAuthenticationManager reactiveAuthenticationManager() {
        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();
        //自定义登录
        managers.add(loginAuthenticationManager());
        //管理员用户密码登录
        managers.add(adminLoginAuthenticationManager());

        return new DelegatingReactiveAuthenticationManager(managers);
    }

    @Bean
    public LoginAuthenticationManager loginAuthenticationManager() {
        return new LoginAuthenticationManager();
    }

    @Bean
    public AdminLoginAuthenticationManager adminLoginAuthenticationManager() {
        return new AdminLoginAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance(); //默认不加密
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

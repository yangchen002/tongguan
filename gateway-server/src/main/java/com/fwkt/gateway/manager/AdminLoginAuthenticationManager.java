package com.fwkt.gateway.manager;

import com.alibaba.fastjson.JSON;
import com.fwkt.gateway.security.token.AdminLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-17 11:04
 */
public class AdminLoginAuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    @Qualifier("adminUserDetails")
    private ReactiveUserDetailsService adminUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if(!(authentication instanceof AdminLoginToken)){
            return Mono.empty();
        }
        System.out.println("===进入Admin登录验证环节====="+ JSON.toJSONString(authentication));
        try {
            final String presentedPassword = (String) authentication.getCredentials();
            return adminUserDetailsService.findByUsername(authentication.getName())
                    .filter(u -> passwordEncoder.matches(presentedPassword, u.getPassword()))
                    .switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("Invalid Credentials"))))
                    .map(u -> new UsernamePasswordAuthenticationToken(u, u.getPassword(), u.getAuthorities()) );
        } catch (Exception e) {
            e.printStackTrace();
            throw  new BadCredentialsException(e.getMessage());
        }
    }
}

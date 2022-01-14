package com.fwkt.gateway.manager;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2022-01-13 15:42
 */
public class LoginAuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    private ReactiveUserDetailsService securityUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if(!(authentication instanceof UsernamePasswordAuthenticationToken)){
            return Mono.empty();
        }
        System.out.println("===进入登录验证环节====="+ JSON.toJSONString(authentication));
        try {
            final String presentedPassword = (String) authentication.getCredentials();
            return securityUserDetailsService.findByUsername(authentication.getName())
                    .filter(u -> this.passwordEncoder.matches(presentedPassword, u.getPassword()))
                    .switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("Invalid Credentials"))))
                    .map(u -> {
                        //userService.updateLoginTime(((AuthUserDetails)u).getUserid());
                        return new UsernamePasswordAuthenticationToken(u, u.getPassword(), u.getAuthorities());
                    } );
        } catch (Exception e) {
            e.printStackTrace();
            throw  new BadCredentialsException(e.getMessage());
        }
    }
}

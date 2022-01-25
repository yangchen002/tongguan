package com.fwkt.gateway.security.service;

import com.fwkt.gateway.entity.AuthUserDetails;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-17 10:54
 */
@Service("adminUserDetails")
public class AdminUserDetailsService implements ReactiveUserDetailsService {
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        System.out.println("==AdminUserDetailsService=findByUsername==>>>>" + username);
        try{
            String adminUser = "22";
            AuthUserDetails user  = new AuthUserDetails();
            if (adminUser != null) {
                user.setUserid("22");
                user.setUsername(username);
                user.setPassword("22");
                List roles  = new ArrayList();
                roles.add("ADMIN");
                roles.add("ROLE_ADMIN");
                roles.add("ROLE_USER");
                user.setRoles(roles);
                user.setAuthorities( AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN,ROLE_ADMIN,ROLE_USER"));
            } else {
                return Mono.error(new UsernameNotFoundException("管理员用户不存在！"));
            }
            return Mono.just(user);
        }catch(Exception e){
            return Mono.error(new RuntimeException("YfSystemException！"+e.getMessage()));
        }
    }
}

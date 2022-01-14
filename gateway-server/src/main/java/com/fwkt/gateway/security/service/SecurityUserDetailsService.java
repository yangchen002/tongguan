package com.fwkt.gateway.security.service;

import com.fwkt.gateway.entity.AuthUserDetails;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-13 15:52
 */
@Primary
@Service
public class SecurityUserDetailsService implements ReactiveUserDetailsService {
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        System.out.println("==SecurityUserDetailsService=findByUsername==>>>>" + username);
        //AccountUserPojo account = userService.getUser(username);
        String account = "11";
        AuthUserDetails userDetails = new AuthUserDetails();
        if (account != null) {
            userDetails.setUserid("11");
            userDetails.setUsername(username);
            userDetails.setPassword("11");
            List roles = new ArrayList();
            roles.add("USER");
            userDetails.setRoles(roles);
            userDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("USER"));
        } else {
            return Mono.error(new RuntimeException());
        }
        return Mono.just(userDetails);
    }
}

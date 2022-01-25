package com.fwkt.gateway.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-17 10:40
 */
public class AdminLoginToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;

    /**
     * 构建没有鉴权的id_token
     * @param principal
     * @param credentials
     */
    public AdminLoginToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(false);
    }

    /**
     * 构建拥有鉴权的id_token
     * @param principal
     * @param authorities
     */
    public AdminLoginToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        // must use super, as we override
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}

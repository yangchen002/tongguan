package com.fwkt.gateway.jwt.exception;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-14 10:36
 */
public class JwtExpiredException extends RuntimeException{
    public JwtExpiredException (String message) {
        super(message);
    }
}

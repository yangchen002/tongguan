package com.fwkt.gateway.jwt.exception;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-14 10:32
 */
public class JwtInvalidException extends RuntimeException{
    public JwtInvalidException(String message) {
        super(message);
    }
}

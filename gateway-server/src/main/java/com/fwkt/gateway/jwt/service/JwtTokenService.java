package com.fwkt.gateway.jwt.service;

import com.fwkt.gateway.jwt.domain.PayloadDto;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.security.core.Authentication;

import java.text.ParseException;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-14 10:04
 */
public interface JwtTokenService {

    /**
     * HMAC算法生成token
     * @param payloadStr
     * @param secret
     * @return
     * @throws JOSEException
     */
    String generateTokenByHMAC(String payloadStr, String secret) throws JOSEException;

    /**
     * HMAC算法校验token
     * @param token
     * @param secret
     * @return
     * @throws ParseException
     * @throws JOSEException
     */
    PayloadDto verifyTokenByHMAC(String token, String secret) throws ParseException, JOSEException;

    /**
     * RSA算法生成token
     * @param payloadStr
     * @param rsaKey
     * @return
     * @throws JOSEException
     */
    String generateTokenByRSA(String payloadStr, RSAKey rsaKey) throws JOSEException;

    /**
     * RSA算法校验token
     * @param token
     * @param rsaKey
     * @return
     * @throws ParseException
     * @throws JOSEException
     */
    PayloadDto verifyTokenByRSA(String token, RSAKey rsaKey) throws ParseException, JOSEException;

    /**
     * 获取默认payload
     * @return
     */
    PayloadDto getDefaultPayloadDto();

    /**
     * 获取默认rsaKey
     * @return
     */
    RSAKey getDefaultRSAKey();

    PayloadDto getPayloadDto(Authentication authentication);
}

package com.fwkt.gateway.web;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.fwkt.commonutils.WebResponse;
import com.fwkt.gateway.entity.AuthUserDetails;
import com.fwkt.gateway.jwt.JWTTokenService;
import com.fwkt.gateway.jwt.domain.PayloadDto;
import com.fwkt.gateway.jwt.service.JwtTokenService;
import com.fwkt.gateway.security.token.AdminLoginToken;
import com.nimbusds.jose.JOSEException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-14 15:25
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/hello")
    public String hello(){
        return "hello word!auth";
    }

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private ReactiveAuthenticationManager reactiveAuthenticationManager;

    @PostMapping("login")
    public Mono<WebResponse> login(UserRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getPhone(), request.getPassword());
        return reactiveAuthenticationManager.authenticate(authenticationToken).map(authentication -> WebResponse.success(getHttpAuthHeaderValue(authentication)));
    }

    @PostMapping("/admin/login")
    public Mono<WebResponse> adminLogin(UserRequest request) {
        AdminLoginToken adminAuthenticationToken = new AdminLoginToken(request.getPhone(),request.getPassword());
        return reactiveAuthenticationManager.authenticate(adminAuthenticationToken).map(authentication -> WebResponse.success(getHttpAuthHeaderValue(authentication, Duration.ofHours(12))));
    }

    @Data
    public class UserRequest {
        private String phone;
        private String password;
    }

    private static Map getHttpAuthHeaderValue(Authentication authentication) {
        Map<String, String> tokenResult = new HashMap<String, String>();
        tokenResult.put("token", String.join(" ", "Bearer", tokenFromAuthentication(authentication)));
        return tokenResult;
    }

    private Map getHttpAuthHeaderValue(Authentication authentication, Duration duration) {
        Map<String, String> tokenResult = new HashMap<String, String>();
        tokenResult.put("token", String.join(" ", "Bearer", tokenFromAuthentication(authentication, duration)));
        return tokenResult;
    }

    private static String tokenFromAuthentication(Authentication authentication) {
        System.out.println("==AuthController==tokenFromAuthentication====>>>" + JSON.toJSONString(authentication));
        return JWTTokenService.generateToken(
                ((AuthUserDetails) authentication.getPrincipal()).getUserid(),
                authentication.getCredentials(),
                authentication.getAuthorities());
    }

    private String tokenFromAuthentication(Authentication authentication, Duration duration) {
        System.out.println("==AuthController==tokenFromAuthentication====>>>" + JSON.toJSONString(authentication));
//        return JWTTokenService.generateToken(
//                ((AuthUserDetails) authentication.getPrincipal()).getUserid(),
//                authentication.getCredentials(),
//                authentication.getAuthorities(),
//                duration);

        try {
            PayloadDto payloadDto = jwtTokenService.getPayloadDto(authentication);
            String token = jwtTokenService.generateTokenByRSA(JSONUtil.toJsonStr(payloadDto),jwtTokenService.getDefaultRSAKey());
            return token;
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return "";
    }
}

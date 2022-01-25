package com.fwkt.jiuwanzi.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-10 15:30
 */
@RestController
@RequestMapping("/test11")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        List list = new ArrayList();

        list.removeIf(m->("11".equals(m)));

        return "hello word1111111111111!";
    }


}

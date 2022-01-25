package com.fwkt.gateway.web;

import com.fwkt.gateway.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangchen
 * @version 1.0
 * @date 2022-01-10 15:30
 */
@RestController
@RequestMapping("/test")
public class TestController {

//    @Autowired
//    private TestEntity testEntity;

//    @GetMapping("/hello")
//    public TestEntity hello(){
//        TestEntity testEntity1 = new TestEntity();
//        testEntity1.setUserId("22");
//        return testEntity1;
//    }
}

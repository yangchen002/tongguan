package com.fwkt.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * TODO
 *
 * @author yangchen
 * @version 1.0
 * @date 2022-01-25 13:33
 */
public class LiuBean {

    private MyBean myBean;

    public LiuBean(MyBean myBean){
        this.myBean = myBean;
    }

    @PostConstruct
    public void init(){
        System.out.println("LiuBean 初始化了");
    }

    public void hello(){
        System.out.println("LiuBean hello");
    }
    public void helloMy() {
        myBean.hello();
    }
}

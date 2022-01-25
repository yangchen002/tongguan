package com.fwkt.gateway.config;

import javax.annotation.PostConstruct;

/**
 * TODO
 *
 * @author yangchen
 * @version 1.0
 * @date 2022-01-25 11:01
 */
public class YourBean {

    public MyBean myBean;

    public YourBean(MyBean myBean){
        this.myBean=myBean;
    }

    @PostConstruct
    public void init(){
        System.out.println("YourBean 初始化了");
    }

    public void hello(){
        System.out.println("YourBean hello");
    }
    public void helloMy() {
        myBean.hello();
    }

}

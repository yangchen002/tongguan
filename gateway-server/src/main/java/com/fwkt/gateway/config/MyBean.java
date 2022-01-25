package com.fwkt.gateway.config;

import javax.annotation.PostConstruct;

/**
 * TODO
 *
 * @author yangchen
 * @version 1.0
 * @date 2022-01-25 10:54
 */
public class MyBean {

    //@PostConstruct该注解被用来修饰一个非静态的void（）方法
    //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次
    //PostConstruct在构造函数之后执行，init（）方法之前执行。
    //Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
    @PostConstruct
    public void init(){
        System.out.println("MyBean初始化了");
    }
    public void hello(){
        System.out.println("Mybean  hello");
    }
}

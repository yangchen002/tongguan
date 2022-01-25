package com.fwkt.gateway.config;

import com.fwkt.gateway.entity.TestEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author yangchen
 * @version 1.0
 * @date 2022-01-25 11:02
 */
@Configuration(proxyBeanMethods = false)
public class ConfigureTest {

    @Bean
    public TestEntity getTestEntity(){
        return new TestEntity();
    }

    @Bean
    public MyBean myBean(){
        return new MyBean();
    }

    @Bean
    public YourBean yourBean(){
        return new YourBean(myBean());
    }

    @Bean
    public LiuBean liuBean(){
        return new LiuBean(myBean());
    }
}

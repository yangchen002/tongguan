package com.fwkt.gateway.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author yangchen
 * @version 1.0
 * @date 2022-01-25 11:04
 */
@Component
public class InitClass implements InitializingBean, ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        LiuBean liuBean = this.applicationContext.getBean(LiuBean.class);
        YourBean bean = this.applicationContext.getBean(YourBean.class); // 第一行
        bean.helloMy();
        YourBean bean1 = this.applicationContext.getBean(YourBean.class);  // 第二行
        MyBean myBean = this.applicationContext.getBean(MyBean.class);    //


        bean.hello();
        myBean.hello();
        bean1.hello();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}

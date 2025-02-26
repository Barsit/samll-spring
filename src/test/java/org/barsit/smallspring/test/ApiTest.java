package org.barsit.smallspring.test;


import org.barsit.smallspring.beans.factory.BeanFactory;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.support.DefaultListableBeanFactory;
import org.barsit.smallspring.test.bean.UserService;
import org.junit.Test;

/**
 * @description:
 * @projectName:samll-spring
 * @see:cn.barsit.smallspring.test
 * @author:db
 * @createTime:2025/2/26 20:17
 * @version:1.0
 */


public class ApiTest {
    @Test
    public void test_BeanFactory(){
        //    初始化bean工厂
        DefaultListableBeanFactory beanFactory =  new DefaultListableBeanFactory();

//    注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("UserService",beanDefinition);
//    获取bean
        UserService userService = (UserService)beanFactory.getBean("UserService");
        userService.queryUserInfo();
    }


}

package org.barsit.smallspring.test;


import org.barsit.smallspring.BeanDefinition;
import org.barsit.smallspring.BeanFactory;
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
        BeanFactory beanFactory =  new BeanFactory();

//    注册bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerDefinitionBean("UserService",beanDefinition);
//    获取bean
        UserService userService = (UserService)beanFactory.getBean("UserService");
        userService.queryUserInfo();
    }


}

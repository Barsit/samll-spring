package org.barsit.smallspring.test;


import org.barsit.smallspring.beans.factory.BeanFactory;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.support.DefaultListableBeanFactory;
import org.barsit.smallspring.test.bean.UserService;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

    @Test
    public void test_BeanFactory2(){
        //    初始化bean工厂
        DefaultListableBeanFactory beanFactory =  new DefaultListableBeanFactory();

//    注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("UserService",beanDefinition);
//    获取bean
//        Java 反射中使用构造函数创建对象实例时，提供的参数必须与构造器的参数类型和顺序完全匹配
//        UserService userService = (UserService) beanFactory.getBean("UserService",18,"小傅哥");
        UserService userService = (UserService) beanFactory.getBean("UserService","小傅哥",18);

        userService.queryUserInfo();
    }
//    4.1 无构造函数
    @Test
    public  void  test_newInstance() throws InstantiationException, IllegalAccessException {
//        Exception: checked unchecked(RuntimeException)
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }
//    4.2 验证有构造函数实例化
    @Test
    public void test_constructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<UserService> declaredConstructor = UserService.class.getDeclaredConstructor(String.class);
        UserService db = declaredConstructor.newInstance("db");
        System.out.println(db);
    }
//4.3 获取构造函数信息
    @Test
    public  void  test_constructors(){
        Constructor<?>[] declaredConstructors = UserService.class.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.getParameterTypes());

        }
    }
//    4.4 Cglib 实例化
//    https://www.runoob.com/w3cnote/cglibcode-generation-library-intro.html

}

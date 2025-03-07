package org.barsit.smallspring.test;


import cn.hutool.core.io.IoUtil;
import org.barsit.smallspring.beans.factory.BeanFactory;
import org.barsit.smallspring.beans.factory.PropertyValue;
import org.barsit.smallspring.beans.factory.PropertyValues;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.factory.BeanReference;
import org.barsit.smallspring.beans.factory.support.DefaultListableBeanFactory;
import org.barsit.smallspring.beans.factory.support.XmlBeanDefinitionReader;
import org.barsit.smallspring.core.io.DefaultResourceLoader;
import org.barsit.smallspring.core.io.Resource;
import org.barsit.smallspring.core.io.ResourceLoader;
import org.barsit.smallspring.test.bean.UserDao;
import org.barsit.smallspring.test.bean.UserService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
        UserService userService = (UserService) beanFactory.getBean("UserService");

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

    @Test
    public void test_BeanFactory3(){
//         // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//         // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));
//        3. UserService
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid","10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);
//        获取
        UserService userService =(UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

//    加载资源
//    获取流
//    转换
//    试用
    private DefaultResourceLoader resourceLoader;
    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_class_path_resource() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);
    }
    @Test
    public void test_file_resource() throws IOException {
        Resource resource = resourceLoader.getResource("D:\\learn\\samll-spring\\src\\test\\resources\\important.properties");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);
    }
    @Test
    public void test_url_resource() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);
    }

    @Test
    public void teat_xml(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        // 3. 获取Bean对象调用方法
        UserService userService = factory.getBean("userService", UserService.class);
        userService.queryUserInfo();


    }
}

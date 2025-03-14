package org.barsit.smallspring.test.bean;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.context.ApplicationContext;
import org.barsit.smallspring.beans.context.ApplicationContextAware;
import org.barsit.smallspring.beans.factory.*;

/**
 * @description:
 * @projectName:samll-spring
 * @see:cn.barsit.smallspring.test.bean
 * @author:db
 * @createTime:2025/2/26 20:17
 * @version:1.0
 */
public class UserService implements Aware, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
    private String name;
    private Integer age;
    private String company;
    private String location;
    private String uid;

    private UserDao userDao;


    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uid) + "company:" +this.company +"   location: " +this.location) ;
//        System.out.println(name);
    }

    public String queryUserInfo(int x) {
        return userDao.queryUserName(uid) + "," + company + "," + location;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append(".....").append(name);
        return sb.toString();
    }

    public UserService(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

//    @Override
//    public void destroy() throws Exception {
//        System.out.println("执行：UserService.destroy");
//    }
//
//    @Override
//    public void afterPropertiesSet() throws BeansException {
//        System.out.println("执行：UserService.afterPropertiesSet");
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader : "  + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
         this.beanFactory  = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}

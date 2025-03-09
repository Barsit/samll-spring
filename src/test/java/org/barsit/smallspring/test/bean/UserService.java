package org.barsit.smallspring.test.bean;

/**
 * @description:
 * @projectName:samll-spring
 * @see:cn.barsit.smallspring.test.bean
 * @author:db
 * @createTime:2025/2/26 20:17
 * @version:1.0
 */
public class UserService {
    private String name;
    private Integer age;
    private String company;
    private String location;
    private String uid;

    private UserDao userDao;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uid) + "company:" +this.company +"   location: " +this.location) ;
//        System.out.println(name);
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
}

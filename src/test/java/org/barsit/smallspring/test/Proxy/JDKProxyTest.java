package org.barsit.smallspring.test.Proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


//1.代理目标实现的接口
interface SMSInterface{
    public void sendSMS();
}

//2. 目标类
class SMSTarget implements SMSInterface {

    @Override
    public void sendSMS() {
        System.out.println("SMSTarget is sending SMS");
    }
}
//3. 实现InvocationHandler 接口 ，也可以简写为 new 实现InvocationHandler
class JDKProxy implements InvocationHandler{
    private Object target;

    JDKProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        猜测proxy来自 Proxy.newProxyInstance 创建的代理对象
//        System.out.println(proxy);
        System.out.println(".........................................");
        System.out.println("Proxy start................................");
        Object result = method.invoke(target, args);
        return result;
    }
}
// 4.Proxy工厂
class ProxyFactory {
    public Object  getProxy(Object target){
//        java.lang.ClassCastException: org.barsit.smallspring.test.Proxy.$Proxy2 cannot be cast to org.barsit.smallspring.test.Proxy.SMSInterface
//        return Proxy.newProxyInstance(Target.class.getClassLoader(),Target.class.getInterfaces(),new JDKProxy(target));
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),new JDKProxy(target));

    }
}
public class JDKProxyTest {
    @Test
    public void jdk_proxy_test(){
        // 并不去编写实现类，而是直接通过JDK提供的一个Proxy.newProxyInstance()创建了一个Hello接口对象,然后将接口方法“代理”给InvocationHandler完成的。
        SMSTarget target = new SMSTarget();
        ProxyFactory proxyFactory = new ProxyFactory();
//        类型强转 获得接口方法
        SMSInterface proxy = (SMSInterface)proxyFactory.getProxy(target);
//      java.lang.StackOverflowError :hash也会被拦截 配合 34行 导致死循环
//        System.out.println(proxy.hashCode());
        System.out.println(".................................");
//        调用接口方法
        proxy.sendSMS();
    }
}

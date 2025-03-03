package org.barsit.smallspring.test.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

//1.实现一个使用阿里云发送短信的类
class ALiSMSService{
    public void sendSMS(String message){
        System.out.println( "AliSMS is sending message   " + message);
    }
}
//2.自定义 MethodInterceptor（方法拦截器）
class MyMethodInterceptor implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Proxy start...........................");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("Proxy end..............................");
        return result;
    }
}

//3.获取代理类
class CglibProxyFactory{
    public Object getProxy(Object target){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MyMethodInterceptor());
        Object o = enhancer.create();
        return o;
    }
}

//4.实际使用
public class CGlibProxyTest {

    @Test
    public void test_cglib_proxy(){
        ALiSMSService aLiSMSService = new ALiSMSService();
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory();
        Object proxy = cglibProxyFactory.getProxy(aLiSMSService);
        aLiSMSService.sendSMS("java");

    }
}

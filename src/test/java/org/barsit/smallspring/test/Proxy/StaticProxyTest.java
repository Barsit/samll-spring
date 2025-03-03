package org.barsit.smallspring.test.Proxy;

import org.junit.Test;

interface ProxyInterface{
    public void proxyMethod();
}

class Target implements ProxyInterface{

    @Override
    public void proxyMethod() {
        System.out.println("this is class target");
    }
}

class Proxy implements ProxyInterface{
    private  Target target;

    Proxy(Target target) {
        this.target = target;
    }

    @Override
    public void proxyMethod() {
        System.out.println("proxy Start.........................");
        target.proxyMethod();
        System.out.println("proxy end............................");
    }
}


public class StaticProxyTest {
    @Test
    public void test_static_proxy() {
//        静态代理 ：代理类的字节码运行前已生成
        Target target = new Target();
        Proxy proxy = new Proxy(target);
        proxy.proxyMethod();
    }
}

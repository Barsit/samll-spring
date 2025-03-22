package org.barsit.smallspring.aop.framework;

import org.barsit.smallspring.aop.AdvisedSupport;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.aop.framework
 * @author:db
 * @createTime:2025/3/22 10:16
 * @version:1.0
 */
public class ProxyFactory {
    private AdvisedSupport advisedSupport;
    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }
    public Object getProxy() {
        return createAopProxy().getProxy();
    }
    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }

}

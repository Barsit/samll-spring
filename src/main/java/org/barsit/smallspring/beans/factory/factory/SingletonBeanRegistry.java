package org.barsit.smallspring.beans.factory.factory;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.factory
 * @author:db
 * @createTime:2025/2/26 20:54
 * @version:1.0
 */
public interface SingletonBeanRegistry {
    public Object getSingleton(String beanName);
    /**
     * 销毁单例对象
     */
    void destroySingletons();

}

package org.barsit.smallspring.beans.factory.factory;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.factory
 * @author:db
 * @createTime:2025/2/26 20:53
 * @version:1.0
 */
public class BeanDefinition {
    private  Class beanClass; //仅注册bean信息，实例化交由容器执行

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}

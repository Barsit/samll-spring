package org.barsit.smallspring.beans.factory.factory;

import org.barsit.smallspring.beans.factory.PropertyValues;

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
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null? propertyValues : new PropertyValues();
    }
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}

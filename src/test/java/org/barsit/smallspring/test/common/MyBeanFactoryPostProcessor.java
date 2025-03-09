package org.barsit.smallspring.test.common;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.ConfigurableListableBeanFactory;
import org.barsit.smallspring.beans.factory.PropertyValue;
import org.barsit.smallspring.beans.factory.PropertyValues;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.factory.BeanFactoryPostProcessor;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.test.common
 * @author:db
 * @createTime:2025/3/9 10:26
 * @version:1.0
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition userService = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = userService.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","ByteDance"));

    }
}

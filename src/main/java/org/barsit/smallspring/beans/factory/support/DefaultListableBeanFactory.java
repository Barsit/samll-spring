package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.ConfigurableListableBeanFactory;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.support
 * @author:db
 * @createTime:2025/2/26 21:16
 * @version:1.0
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();
    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null) throw new BeansException("no bean named '"+ beanName + "' is defined");
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name,beanDefinition);

    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }
}

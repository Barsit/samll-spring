package org.barsit.smallspring.beans.context.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.context.ApplicationContext;
import org.barsit.smallspring.beans.context.ApplicationContextAware;
import org.barsit.smallspring.beans.factory.factory.BeanPostProcessor;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return  bean;
    }
}

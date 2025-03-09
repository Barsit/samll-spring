package org.barsit.smallspring.beans.context.support;

import org.barsit.smallspring.beans.factory.support.DefaultListableBeanFactory;
import org.barsit.smallspring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.context.support
 * @author:db
 * @createTime:2025/3/8 20:10
 * @version:1.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}

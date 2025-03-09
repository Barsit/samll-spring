package org.barsit.smallspring.beans.context.support;

import org.barsit.smallspring.beans.BeansException;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.context.support
 * @author:db
 * @createTime:2025/3/8 20:10
 * @version:1.0
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private String[] configLocations;
    public ClassPathXmlApplicationContext() {
    }
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }
    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}

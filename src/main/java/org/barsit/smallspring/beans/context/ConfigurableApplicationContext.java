package org.barsit.smallspring.beans.context;

import org.barsit.smallspring.beans.BeansException;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.context
 * @author:db
 * @createTime:2025/3/8 20:08
 * @version:1.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh() throws BeansException;
    void registerShutdownHook();

    void close();
}

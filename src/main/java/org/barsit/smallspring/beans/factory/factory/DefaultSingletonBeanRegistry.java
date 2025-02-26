package org.barsit.smallspring.beans.factory.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.factory
 * @author:db
 * @createTime:2025/2/26 21:03
 * @version:1.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> singletonObjects = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
       return singletonObjects.get(beanName);
    }
    protected void addSingeton(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }

}

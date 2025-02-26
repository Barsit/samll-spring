package org.barsit.smallspring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.samllspring
 * @author:db
 * @createTime:2025/2/26 19:58
 * @version:1.0
 */
public class BeanFactory {
    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(); // 为什么用ConcurrentHashMap？
//    获取bean
    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }
//    注册bean
    public void registerDefinitionBean(String name,BeanDefinition beanDefinition){
        beanDefinitionMap.put(name, beanDefinition);
    }

}

package org.barsit.smallspring.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.PropertyValue;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.factory.BeanReference;
import org.barsit.smallspring.beans.factory.support.AbstractBeanDefinitionReader;
import org.barsit.smallspring.beans.factory.support.BeanDefinitionRegistry;
import org.barsit.smallspring.core.io.DefaultResourceLoader;
import org.barsit.smallspring.core.io.Resource;
import org.barsit.smallspring.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, DefaultResourceLoader defaultResourceLoader) {
        super(registry, defaultResourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try(InputStream stream=resource.getInputStream()){
                doLoadInputStream(stream);
            }
        }catch (IOException |ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from "+ resource,e);
        }
    }

    private void doLoadInputStream(InputStream stream) throws ClassNotFoundException {
//        解析XMl
        Document document = XmlUtil.readXML(stream);
        Element element = document.getDocumentElement();
        NodeList childNodes = element.getChildNodes();
        for(int i = 0 ; i < childNodes.getLength(); i++ ){
            //            判断元素
            if(!(childNodes.item(i) instanceof Element)) continue;
            //            判断对象
            if(!"bean".equals(childNodes.item(i).getNodeName())) continue;

//            解析标签
           Element bean = (Element)childNodes.item(i);
           String name = bean.getAttribute("name");
           String id = bean.getAttribute("id");
           String clazz = bean.getAttribute("class");
            String initMethod = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");
            String beanScope = bean.getAttribute("scope");

//           id 优先级大于name
            String beanName = StrUtil.isNotEmpty(id)?id:name;
            if(StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz);
            }
            //定义beanDefinition
            Class<?> aClass = Class.forName(clazz);
            BeanDefinition beanDefinition = new BeanDefinition(aClass);

            if(StrUtil.isNotEmpty(beanScope)){
                beanDefinition.setScope(beanScope);
            }

            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);


            for(int j = 0; j < bean.getChildNodes().getLength();j++){
                if(!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if(!("property".equals(bean.getChildNodes().item(j).getNodeName()))) continue;

//                解析依赖
                Element property =(Element)bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
//            填充属性
                Object value = StrUtil.isNotEmpty(attrRef)?new BeanReference(attrRef):attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
//        注册Bean信息
            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("Duplicated beanName :["+beanName+"] is not allowed");
            }
            getRegistry().registerBeanDefinition(beanName,beanDefinition);
        }

    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }

    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }
}

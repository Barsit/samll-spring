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
import org.w3c.dom.Node;
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
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength();i++ ){
//            判断元素
            if(!(childNodes.item(i) instanceof Element)) continue;
//            判断对象
            if(!("bean".equals(childNodes.item(i).getNodeName()))) continue;
            Element bean = (Element)childNodes.item(i);

            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            Class<?> aClass = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id)?id:name;

            if(StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(aClass.getSimpleName());
            }
//定义bean
            BeanDefinition beanDefinition = new BeanDefinition(aClass);
//            填充属性
            for(int j= 0 ;j < bean.getChildNodes().getLength();j++){
                if(!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if(!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;

                Element property = (Element)bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                Object value= StrUtil.isNotEmpty(attrRef)?new BeanReference(attrRef): attrValue;
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
}

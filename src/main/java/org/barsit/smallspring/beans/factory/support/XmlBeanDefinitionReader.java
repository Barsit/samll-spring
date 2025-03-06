package org.barsit.smallspring.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.core.io.DefaultResourceLoader;
import org.barsit.smallspring.core.io.Resource;
import org.barsit.smallspring.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

    protected XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
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

        }
//        注册Bean信息
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

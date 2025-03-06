package org.barsit.smallspring.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader{

    @Override
    public Resource getResource(String location) {
//        通过三种方式获取资源
        Assert.notNull(location,"location must not be null");
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        try {
            URL url = new URL(location);
            return new UrlResource(url);
        }catch (MalformedURLException e) {
            return new FileSystemResource(location);
        }
    }
}

package org.barsit.smallspring.core.io;

import cn.hutool.core.lang.Assert;
import org.barsit.smallspring.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{
    private final String path;

    public ClassPathResource(String path, ClassLoader classLoader) {
//        路径判空
        Assert.notNull(path,"Path must not be null");
        this.path = path;
        this.classLoader = classLoader!=null?classLoader: ClassUtils.getDefaultClassLoader();
    }

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    @Override
    public InputStream getInputStream() throws IOException {
//      核心方法  getResourceAsStream
        InputStream is = classLoader.getResourceAsStream(path);
        if(is==null){
            throw new FileNotFoundException(
                    this.path + "cannot be opened because it does not exist"
            );
        }
        return is;

    }
}

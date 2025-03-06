package org.barsit.smallspring.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements Resource{
//    url
    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url,"url must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
//        打开链接
//        get input
        URLConnection urlConnection = this.url.openConnection();
        try {
            return  urlConnection.getInputStream();
        }catch (IOException e){
            if(urlConnection instanceof HttpURLConnection){
                ((HttpURLConnection)urlConnection).disconnect();
            }
            throw e;
        }
    }

    public URL getUrl() {
        return url;
    }
}

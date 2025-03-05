package org.barsit.smallspring.core.io;

public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";
    public Resource getResource(String location);
}

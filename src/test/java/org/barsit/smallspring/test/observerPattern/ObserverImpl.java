package org.barsit.smallspring.test.observerPattern;

import java.util.Observable;
import java.util.Observer;

public class ObserverImpl implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("hello world");
    }
}

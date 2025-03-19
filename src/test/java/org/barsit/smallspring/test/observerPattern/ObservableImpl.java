package org.barsit.smallspring.test.observerPattern;

import java.util.Observable;

public class ObservableImpl extends Observable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        setChanged();
        notifyObservers();
    }
}

package org.barsit.smallspring.beans.factory;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private List<PropertyValue> propertyValuesList =new ArrayList<>();

    public PropertyValue[] getPropertyValuesList() {
        return propertyValuesList.toArray(new PropertyValue[0]);
    }

    public void addPropertyValue(PropertyValue pv){
        propertyValuesList.add(pv);
    }
    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue propertyValue : propertyValuesList) {
            if(propertyValue.getName()==propertyName){
                return propertyValue;
            }
        }
        return null;
    }
}

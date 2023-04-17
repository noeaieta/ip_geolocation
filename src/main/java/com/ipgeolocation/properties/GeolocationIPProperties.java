package com.ipgeolocation.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class GeolocationIPProperties {
	 
    private Properties properties = null;
     
    public GeolocationIPProperties(){
         
        InputStream file = null;
        try {
            this.properties = new Properties();
            file = this.getClass().getResourceAsStream("apikeys.properties");
            properties.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    public Set<Object> getAllKeys(){
        Set<Object> keys = properties.keySet();
        return keys;
    }
     
    public String getPropertyValue(String key){
        return this.properties.getProperty(key);
    }
     
}

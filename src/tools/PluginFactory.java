package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Properties;

public class PluginFactory {

    private Properties properties;

    public PluginFactory(Properties properties) {
        this.properties = properties;
    }
    
    public Object getPlugin(Class classObject) {
        String type = classObject.getName();
        String className = properties.getProperty(type);
        if (className == null) {
            throw new RuntimeException("?implementation not found: " + type);
        }
        try {
            return Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("?factory unable to construct instance of " + type);
        }
    }
}

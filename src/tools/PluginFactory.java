package tools;

import java.util.Properties;

public class PluginFactory<T> {

    private Properties properties;

    public PluginFactory(Properties properties) {
        this.properties = properties;
    }
    
    public T getPlugin(Class<T> classObject) {
        String type = classObject.getName();
        String className = properties.getProperty(type);
        if (className == null) {
            throw new RuntimeException("?implementation not found: " + type);
        }
        try {
            return (T) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("?factory unable to construct instance of " + type);
        }
    }
}

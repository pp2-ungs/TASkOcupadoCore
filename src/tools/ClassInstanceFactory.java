package tools;

import java.util.Properties;

public class ClassInstanceFactory<T> {

    private Properties properties;

    public ClassInstanceFactory(Properties properties) {
        this.properties = properties;
    }
    
    public T instance(Class<T> classObject) {
        String type = classObject.getName();
        String className = properties.getProperty(type);
        if (className == null) {
            throw new RuntimeException("?implementation not found: " + type);
        }
        try {
            return (T) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("?unable to construct instance of " + type);
        }
    }
}

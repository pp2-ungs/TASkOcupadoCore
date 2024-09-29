package core;

import java.io.FileInputStream;
import java.util.Properties;

public class PluginFactory {

    private static Properties props = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream(CoreSettings.RESOURCES + "config.properties");
            props.load(fis);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Object loadInstance(Class iface) {
        String implName = props.getProperty(iface.getName());
        if (implName == null) {
            throw new RuntimeException("?implementation not specified for "
                    + iface.getName() + " in PluginFactory properties.");
        }
        try {
            return Class.forName(implName).newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("?factory unable to construct instance of " + iface.getName());
        }
    }
}

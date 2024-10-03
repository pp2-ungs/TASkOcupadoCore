package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// ???
// Preguntar a Javier, si está bien así.
//
// En el PEAA, está con `properties` como variable estática, y `getPlugin()`
// como método estático, y hardcodea el path del archivo de configuración.
//
// Para mí, está mal que esté el path hardcodeado, por eso lo reescribí como
// variable de instancia, y con método de instancia, para que se le pueda pasar
// el path al constructor.

// Responsabilidad: obtener una implementación de una "Separated Interface"
// en runtime.
public class PluginFactory {

    private Properties properties;

    public PluginFactory(String configFileName) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(configFileName));
        } catch (IOException e) {
            System.out.println("?plugin not working: I/O error");
        }
    }

    public Object getPlugin(Class classObject) {
        String className = properties.getProperty(classObject.getName());
        if (className == null) {
            throw new RuntimeException("?implementation not found: " + classObject.getName());
        }
        try {
            return Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("?factory unable to construct instance of " + classObject.getName());
        }
    }

}

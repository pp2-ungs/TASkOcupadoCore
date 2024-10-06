package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

// NOTA IMPORTANTE: esto me parece que no tiene que ser de tipo T. 
// getPlugin tiene sentido que reciba un object, ya que le estamos 
// diciendo qué object recibir.

// si esto es de tipo T, es rarísimo, porque esto devuelve un DataSetLoader,
// también de tipo T.

// hay que ver si hay alguna justificación que convenza que esto NO es de tipo
// T, y agarrarnos a eso o pensar cómo hacer que ande.
public class PluginFactory {

    private Properties properties;

    public PluginFactory(Properties properties) {
        this.properties = properties;
    }

    // queda así
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

package core;

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
public class PluginFactory<T> {

    private Properties properties;

    public PluginFactory(String configFileName) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(configFileName));
        } catch (IOException e) {
            System.out.println("?plugin not working: I/O error");
        }
    }

    public T getPlugin() {
        String className = properties.getProperty(getGenericType().getName());
        if (className == null) {
            throw new RuntimeException("?implementation not found: " + getGenericType().getName());
        }
        try {
            return (T) Class.forName(getGenericType().getName()).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("?factory unable to construct instance of " + getGenericType().getName());
        }
    }
    
    
    // revisar: añadido a lo bruto para obtener el tipo de T
    private Class<?> getGenericType() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            Type[] typeArguments = ((ParameterizedType) superclass).getActualTypeArguments();
            return (Class<?>) typeArguments[0];
        }
        throw new RuntimeException("Cannot resolve generic type");
    }

}

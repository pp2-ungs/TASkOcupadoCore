package tools;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class Instantiator {

    public <T> Set<T> instantiate(Set<Class<? extends T>> classes) {
        Set<T> instances = new HashSet<>();
        for (Class<? extends T> cls : classes) {
            try {
                instances.add(cls.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                System.err.println("Error al instanciar la clase " + cls.getName() + ": " + e.getMessage());
            }
        }
        return instances;
    }
}

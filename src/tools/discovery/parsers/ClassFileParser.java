package tools.discovery.parsers;

import tools.discovery.ResourceParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class ClassFileParser implements ResourceParser {
    @Override
    public boolean supports(Path resource) {
        return resource.toString().endsWith(".class");
    }

    @Override
    public <T> Set<Class<? extends T>> parse(Path resource, Class<T> type) {
        Set<Class<? extends T>> foundClasses = new HashSet<>();
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{resource.getParent().toUri().toURL()});
            String className = resource.getFileName().toString().replace(".class", "");

            Class<?> cls = Class.forName(className, false, classLoader);
            if (type.isAssignableFrom(cls) && !cls.isInterface()) {
                foundClasses.add(cls.asSubclass(type));
            }
        } catch (MalformedURLException | ClassNotFoundException | NoClassDefFoundError e) {
            // Ignorar errores de carga
        }
        return foundClasses;
    }

}

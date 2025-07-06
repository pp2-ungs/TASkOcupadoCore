package tools.discovery.parsers;

import tools.discovery.ResourceParser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class ClassFileParser implements ResourceParser {

    private static final String SUPPORTED_EXTENSION = ".class";

    @Override
    public boolean supports(Path resource) {
        return resource.toString().endsWith(SUPPORTED_EXTENSION);
    }

    @Override
    public <T> Set<Class<T>> parse(Path resource, Path basePath, Class<T> type) {
        Set<Class<T>> foundClasses = new HashSet<>();
        try {
            URL[] urls = { basePath.toUri().toURL() };
            // Obtener el classloader que cargó esta misma clase.
            ClassLoader parentClassLoader = getClass().getClassLoader();
            URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);

            // Calcular el nombre de la clase a partir de la ruta relativa a la base
            Path relativePath = basePath.relativize(resource);
            String className = relativePath.toString()
                    .replace(File.separator, ".")
                    .replace(SUPPORTED_EXTENSION, "");

            Class<?> cls = Class.forName(className, false, classLoader);
            if (type.isAssignableFrom(cls) && !cls.isInterface()) {
                foundClasses.add((Class<T>) cls);
            }
        } catch (MalformedURLException | ClassNotFoundException | NoClassDefFoundError e) {
            System.err.println("Error en ClassFileParser: " + e.getMessage());
        }
        return foundClasses;
    }
}

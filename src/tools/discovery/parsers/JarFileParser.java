package tools.discovery.parsers;

import tools.discovery.ResourceParser;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileParser implements ResourceParser {

    private static final String SUPPORTED_EXTENSION = ".jar";

    @Override
    public boolean supports(Path resource) {
        return resource.toString().endsWith(SUPPORTED_EXTENSION);
    }

    @Override
    public <T> Set<Class<T>> parse(Path resource, Path basePath, Class<T> type) {
        Set<Class<T>> classes = new HashSet<>();
        try (JarFile jar = new JarFile(resource.toFile())) {
            URL[] urls = { resource.toUri().toURL() };
            URLClassLoader classLoader = new URLClassLoader(urls);

            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                    continue;
                }
                String className = entry.getName().substring(0, entry.getName().length() - 6).replace('/', '.');
                try {
                    Class<?> cls = classLoader.loadClass(className);
                    if (type.isAssignableFrom(cls) && !cls.isInterface()) {
                        classes.add((Class<T>) cls);
                    }
                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                    System.err.println("Error en JarFileParser: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo jar: " + resource);
        }
        return classes;
    }
}

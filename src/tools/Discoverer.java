package tools;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Discoverer {

    private File directory;
    
    public Discoverer(String path) {
        directory = new File(path);
        
        if (!directory.exists() || !directory.isDirectory()) {
            throw new RuntimeException("?invalid directory: " + path);
        }
    }
    
    public <T> Set<T> discover(Class<T> type) {
        Set<T> discoveredImpls = new HashSet<>();
        findClassesInPath(directory, type, discoveredImpls);
        return discoveredImpls;
    }
    
    private <T> void findClassesInPath(File path, Class<T> type, Set<T> discoveredImpls) {
        if (path.isDirectory()) {
            File[] files = path.listFiles();
            if (files != null) {
                
                for (File file : files) {
                    findClassesInPath(file, type, discoveredImpls);
                }
            }
        } else if (path.isFile() && path.getName().endsWith(".jar")) {
            discoveredImpls.addAll(findImplementationsInJar(path, type));
        }
    }

    private <T> Set<T> findImplementationsInJar(File jarFile, Class<T> type) {
        Set<T> implementations = new HashSet<>();
        
        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    instantiateClassFromJar(jarFile, entry, type, implementations);
                }
            }
        } catch (IOException e) {
            System.err.println("?error reading jar file: " + e.getMessage());
        }
        return implementations;
    }

    private <T> void instantiateClassFromJar(File jarFile, JarEntry entry, Class<T> type, Set<T> discoveredImpls) {
        try {
            if (entry.getName().endsWith(".class") && !entry.getName().contains("module-info") && !entry.getName().contains("META-INF")) {
                Class<?> cls = loadClassFromJar(jarFile, entry.getName());
                if (cls != null && type.isAssignableFrom(cls) && !cls.isInterface()) {
                    discoveredImpls.add((T) cls.getDeclaredConstructor().newInstance());
                }
            }
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException
                | IllegalAccessException e) {
            System.err.println("?error instantiating class: " + e.getMessage());
        }
    }

    private Class<?> loadClassFromJar(File jarFile, String className) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{jarFile.toURI().toURL()});
            String canonicalClassName = className.replace("/", ".").replace(".class", "");
            return Class.forName(canonicalClassName, true, classLoader);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("?error loading class from jar: " + e.getMessage());
            return null;
        }
    }
}

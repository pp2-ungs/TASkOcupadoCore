package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

// FIXME
// X: aca no hay que hacer nada, no?
public class Discoverer<T> {
    File directory;
    
    public Discoverer(String path) {
        directory = new File(path);
        
        if (!directory.exists() || !directory.isDirectory()) {
            //throw new FileNotFoundException("?invalid directory: " + path);
            throw new RuntimeException("?invalid directory: " + path);
        }
    }
    

    public Set<T> discover(String path) {
        Set<T> implementations = new HashSet<>();
        //findClassesInPath(directory, T, implementations);
        return implementations;
    }

    // FIXME: otra vez, obtener el tipo de T...
    private void findClassesInPath(File path, Class<?> targetInterface, Set<Object> implementations) {
        if (path.isDirectory() && !path.getName().equals("lib")) {
            File[] files = path.listFiles();
            if (files != null) {
                
                for (File file : files) {
                    findClassesInPath(file, targetInterface, implementations);
                }
            }
        } else if (path.isFile() && path.getName().endsWith(".jar")) {
            implementations.addAll(findImplementationsInJar(path, targetInterface));
        }
    }

    private Set<T> findImplementationsInJar(File jarFile, Class<?> targetInterface) {
        Set<T> implementations = new HashSet<>();

        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    instantiateClassFromJar(jarFile, entry, targetInterface, implementations);
                }
            }
        } catch (IOException e) {
            System.err.println("?error reading jar file: " + e.getMessage());
        }

        return implementations;
    }

    private void instantiateClassFromJar(File jarFile, JarEntry entry, Class<?> targetInterface, Set<Object> implementations) {
        try {
            if (entry.getName().endsWith(".class") && !entry.getName().contains("module-info") && !entry.getName().contains("META-INF")) {
                Class<?> cls = loadClassFromJar(jarFile, entry.getName());
                if (cls != null && targetInterface.isAssignableFrom(cls) && !cls.isInterface()) {
                    implementations.add(cls.getDeclaredConstructor().newInstance());
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

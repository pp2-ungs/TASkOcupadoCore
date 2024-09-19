package discovery;

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

public class Discoverer {
/*
	@SuppressWarnings("deprecation")
	public static Set<Object> discover(String path, String packageName, Class<?> targetInterface) throws FileNotFoundException {

		File directory = new File(path);
		Set<Object> foundClasses = new HashSet<>();

		if (!directory.exists() || !directory.isDirectory()) {
			throw new FileNotFoundException("The specified directory is not valid: " + path);
		}

		for (File file : directory.listFiles()) {
			if (file.getName().endsWith(".class")) {

				String className = file.getName().substring(0, file.getName().length() - 6);

				Class<?> cls;
				try {
					cls = Class.forName(packageName + "." + className);

					if (targetInterface.isAssignableFrom(cls) && !cls.isInterface()) {
						foundClasses.add(cls.newInstance());
					}
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return foundClasses;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(discover("bin/ext", "ext", Notificator.class));
	}
*/
	
	public static Set<Object> discover(String path, Class<?> targetInterface) throws FileNotFoundException {
        File directory = new File(path);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new FileNotFoundException("The specified directory is not valid: " + path);
        }

        Set<Object> implementations = new HashSet<>();
        findClassesInPath(directory, targetInterface, implementations);
        return implementations;
    }

    private static void findClassesInPath(File path, Class<?> targetInterface, Set<Object> implementations) {
        if (path.isDirectory()) {
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

    private static Set<Object> findImplementationsInJar(File jarFile, Class<?> targetInterface) {
        Set<Object> implementations = new HashSet<>();

        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    instantiateClassFromJar(jarFile, entry, targetInterface, implementations);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading jar file: " + e.getMessage());
        }

        return implementations;
    }

    private static void instantiateClassFromJar(File jarFile, JarEntry entry, Class<?> targetInterface, Set<Object> implementations) {
        try {
            if (entry.getName().endsWith(".class") && !entry.getName().contains("module-info") && !entry.getName().contains("META-INF")) {
                Class<?> cls = loadClassFromJar(jarFile, entry.getName());
                if (cls != null && targetInterface.isAssignableFrom(cls) && !cls.isInterface()) {
                    implementations.add(cls.getDeclaredConstructor().newInstance());
                }
            }
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            System.err.println("Error instantiating class: " + e.getMessage());
        }
    }

    private static Class<?> loadClassFromJar(File jarFile, String className) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{jarFile.toURI().toURL()});
            String canonicalClassName = className.replace("/", ".").replace(".class", "");
            return Class.forName(canonicalClassName, true, classLoader);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading class from jar: " + e.getMessage());
            return null;
        }
    }
}

package discovery;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import core.Notificator;

public class Discoverer {

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

}

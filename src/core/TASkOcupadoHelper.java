package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import observer.Observer;
import tools.Discoverer;
import tools.PluginFactory;

// Responsabilidad: inicializar el Core.
public class TASkOcupadoHelper {

    private Properties properties;

    public TASkOcupadoHelper(String path) {
        this.properties = new Properties();

        path = path == null || path.isEmpty() ? CoreSettings.PROPERTIES_FILE : path;

        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("?properties I/O error: " + path);
        }
    }

    public Set<Observer> getObservers() {
        Discoverer discoverer = new Discoverer(CoreSettings.EXTENSIONS);
        Set<Observer> observers = discoverer.discover(Observer.class);
        return observers;
    }

    private ContentLoader getLoader() {
        PluginFactory plugin = new PluginFactory(properties);
        ContentLoader loader = (ContentLoader) plugin.getPlugin(ContentLoader.class);
        return loader;
    }

    public Set<Task> getTasks() {
        return getLoader().loadSet(Task.class);
    }

    public Set<Member> getMembers() {
        return getLoader().loadSet(Member.class);
    }

}

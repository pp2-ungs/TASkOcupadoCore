package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import observer.Observer;
import tools.Discoverer;
import tools.PluginFactory;

public class CoreFactory {

    private Properties properties;

    public CoreFactory(String path) {
        this.properties = new Properties();

        path = path == null || path.isEmpty() ? Settings.PROPERTIES_FILE : path;

        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            System.err.println("?properties I/O error: " + path);
        }
    }
    
    public TASkOcupado create() {
        Set<Task> tasks = getTasks();
        Set<Member> members = getMembers();
        
        Set<Observer> taskAssignerObservers = getObservers();
        TaskAssigner taskAssigner = new TaskAssigner(taskAssignerObservers);
        
        return new TASkOcupado(tasks, members, taskAssigner);
    }

    public Set<Observer> getObservers() {
        Discoverer discoverer = new Discoverer(Settings.EXTENSIONS);
        Set<Observer> observers = discoverer.discover(Observer.class);
        return observers;
    }

    public Set<Task> getTasks() {
        return getLoader().loadSet(Task.class);
    }

    public Set<Member> getMembers() {
        return getLoader().loadSet(Member.class);
    }
    
    private ContentLoader getLoader() {
        PluginFactory<ContentLoader> plugin = new PluginFactory<ContentLoader>(properties);
        ContentLoader<?> loader = plugin.getPlugin(ContentLoader.class);
        return loader;
    }
}

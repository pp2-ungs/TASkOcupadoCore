package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import observer.Observer;
import tools.Discoverer;
import tools.PluginFactory;

public class CoreFactory {

    private Properties properties;
    private ContentLoader loader;

    public CoreFactory(String path) {
        this.properties = new Properties();

        path = path == null || path.isEmpty() ? Settings.PROPERTIES_FILE : path;

        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            System.err.println("?properties I/O error: " + path);
        }
        
        loader = getLoader();
    }
    
    public TASkOcupado create() {
        Set<Task> tasks = getTasks();
        Set<Member> members = getMembers();
        
        Set<Observer> taskAssignerObservers = getObservers();
        TaskAssigner taskAssigner = new TaskAssigner(taskAssignerObservers);
        
        return new TASkOcupado(tasks, members, taskAssigner);
    }

    private Set<Observer> getObservers() {
        Discoverer<Observer> discoverer = new Discoverer<>(Settings.EXTENSIONS);
        Set<Observer> observers = discoverer.discover(Observer.class);
        return observers;
    }

     private Set<Task> getTasks() {
        return loader.loadSet(Task.class);
    }

    private Set<Member> getMembers() {
        return loader.loadSet(Member.class);
    }

    private ContentLoader getLoader() {
        PluginFactory<ContentLoader> plugin = new PluginFactory<>(properties);
        return plugin.getPlugin(ContentLoader.class);
    }
}

package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import observer.Observer;
import tools.Discoverer;
import tools.PluginFactory;

public class TASkOcupadoFactory {

    private Properties properties;

    public TASkOcupadoFactory(String propertiesPath) {
        this.properties = new Properties();

        propertiesPath = propertiesPath == null || propertiesPath.isEmpty() ? Settings.PROPERTIES_FILE : propertiesPath;

        try {
            properties.load(new FileInputStream(propertiesPath));
        } catch (IOException e) {
            System.err.println("?properties I/O error: " + propertiesPath);
        }
    }

    public TASkOcupado create() {
        Set<Task> tasks = loadSetOfTasks();
        Set<Person> people = loadSetOfPeople();
    
        TASkOcupado taskOcupado = new TASkOcupado(tasks, people);
        
        Set<Observer> observers = loadSetOfObservers();
        observers.forEach(observer -> taskOcupado.addObserver(observer));
        
        System.out.println(observers);
        
        return taskOcupado;
    }

    private ContentLoader getContentLoader() {
        var pluginFactory = new PluginFactory(properties);
        return pluginFactory.create(ContentLoader.class);
    }

    private Set<Task> loadSetOfTasks() {
        return getContentLoader().loadSetOf(Task.class);
    }

    private Set<Person> loadSetOfPeople() {
        return getContentLoader().loadSetOf(Person.class);
    }
    
    private Set<Observer> loadSetOfObservers() {        
        Discoverer discoverer = new Discoverer(Settings.EXTENSIONS);
        return discoverer.discover(Observer.class);
    }

}

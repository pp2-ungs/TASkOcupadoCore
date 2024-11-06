package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import observer.Observer;
import tools.Discoverer;
import tools.ClassInstanceFactory;

public class CoreFactory {

    private Properties properties;

    public CoreFactory(String propertiesPath) {
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
        
        Set<Observer> observers = searchObservers();
        observers.forEach(observer -> taskOcupado.addObserver(observer));
        
        return taskOcupado;
    }

    private Set<Observer> searchObservers() {        
        Discoverer<Observer> discoverer = new Discoverer<>(Settings.EXTENSIONS, Observer.class);
        discoverer.discover();
        return discoverer.getDiscoveredImpls();
    }

    private ContentLoader getContentLoader() {
        var classInstanceFactory = new ClassInstanceFactory<ContentLoader>(properties);
        return classInstanceFactory.create(ContentLoader.class);
    }

    private Set<Task> loadSetOfTasks() {
        return getContentLoader().loadSetOf(Task.class);
    }

    private Set<Person> loadSetOfPeople() {
        return getContentLoader().loadSetOf(Person.class);
    }

}

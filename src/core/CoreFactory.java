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
        Set<Task> tasks = createTasks();
        Set<Person> members = createPeople();
        Set<Observer> observers = searchObservers();
        return new TASkOcupado(tasks, members, observers);
    }

    private Set<Observer> searchObservers() {        
        Discoverer<Observer> discoverer = new Discoverer<>(Settings.EXTENSIONS, Observer.class);
        discoverer.discover();
        return discoverer.getDiscoveredImpls();
    }

    private ContentFactory getContentFactory() {
        var classInstanceFactory = new ClassInstanceFactory<ContentFactory>(properties);
        return classInstanceFactory.instance(ContentFactory.class);
    }

    private Set<Task> createTasks() {
        return getContentFactory().createSetOf(Task.class);
    }

    private Set<Person> createPeople() {
        return getContentFactory().createSetOf(Person.class);
    }

}

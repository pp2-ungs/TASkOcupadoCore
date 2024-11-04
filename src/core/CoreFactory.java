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
        Set<Task> tasks = createTasks();
        Set<Person> members = createPeople();
        Set<Observer> observers = searchObservers();
        System.out.println(observers);
        return new TASkOcupado(tasks, members, observers);
    }

    private Set<Observer> searchObservers() {
        // ???
        // Si vamos a usar properties, Discoverer debería tomar un properties.
        // De esta manera, se justifican un poco más los archivos properties.
        //var discoverer = new Discoverer<Observer>(properties); // FIXME
        
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

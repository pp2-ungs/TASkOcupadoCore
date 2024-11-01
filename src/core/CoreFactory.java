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
        Set<Task> tasks = getTasks();
        Set<Person> members = getMembers();

        Set<Observer> taskAssignerObservers = getObservers();
        TaskAssigner taskAssigner = new TaskAssigner(taskAssignerObservers);

        return new TASkOcupado(tasks, members, taskAssigner);
    }

    private Set<Observer> getObservers() {
        Discoverer<Observer> discoverer = new Discoverer<>(Settings.EXTENSIONS);
        Set<Observer> observers = discoverer.discover(Observer.class);
        return observers;
    }

    private ContentFactory getContentFactory() {
        var classInstanceFactory = new ClassInstanceFactory<ContentFactory>(properties);
        return classInstanceFactory.instance(ContentFactory.class);
    }

    private Set<Task> getTasks() {
        return getContentFactory().createSetOf(Task.class);
    }

    private Set<Person> getMembers() {
        return getContentFactory().createSetOf(Person.class);
    }

}

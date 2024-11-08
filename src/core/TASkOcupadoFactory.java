package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import notifier.Notifier;
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
        
        Set<Notifier> notifiers = loadSetOfNotifiers();
        notifiers.forEach(notifier -> taskOcupado.addObserver(notifier));
        
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
    
    private Set<Notifier> loadSetOfNotifiers() {        
        Discoverer discoverer = new Discoverer(Settings.EXTENSIONS);
        return discoverer.discover(Notifier.class);
    }

}

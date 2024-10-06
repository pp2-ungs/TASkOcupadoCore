package core;

import observer.Observer;
import tools.PluginFactory;
import tools.Discoverer;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class TASkOcupadoFactory {
    private Properties properties;
    
    
    public TASkOcupadoFactory(String propertiesPath) {
        properties = new Properties();
        
        String path = (propertiesPath == null || propertiesPath.isEmpty()) ? Settings.PROPERTIES_FILE : propertiesPath;
        
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("?plugin not working: I/O error");
        }
    }
    
    // el properties no es usado acá...
    public Set<Observer> getObservers() {
       Discoverer<Observer> discoverer = new Discoverer(Settings.EXTENSIONS);
       
       return discoverer.discover(Observer.class);
    }
    
    // FIXME: codigo repetido
    public Set<Task> getTasks() {
        PluginFactory pluginFactory = new PluginFactory(properties);
         
        // es un poco sucio...
        ContentLoader<Task> tasksLoader = (ContentLoader<Task>) pluginFactory.getPlugin(ContentLoader.class);
        return tasksLoader.loadSet(Task.class);
    }
    
    public Set<Member> getMembers() {
        PluginFactory pluginFactory = new PluginFactory(properties);
         
        // es un poco sucio...
        ContentLoader<Member> membersLoader = (ContentLoader<Member>) pluginFactory.getPlugin(ContentLoader.class);
        return membersLoader.loadSet(Member.class);
    }
    
}

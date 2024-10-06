package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class TASkOcupadoFactory {
    private Properties properties;
    
    
    public TASkOcupadoFactory(String propertiesPath) {
        properties = new Properties();
        
        String path = (propertiesPath == null || propertiesPath.isEmpty()) ? CoreSettings.PROPERTIES_FILE : propertiesPath;
        
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("?plugin not working: I/O error");
        }
    }
    
    // el properties no es usado acá...
    public Set<Observer> getObservers() {
       Discoverer<Observer> discoverer = new Discoverer(CoreSettings.EXTENSIONS);
       
       return discoverer.discover(Observer.class);
    }
    
    // FIXME: codigo repetido
    public Set<Task> getTasks() {
        PluginFactory pluginFactory = new PluginFactory(properties);
         
        // es un poco sucio...
        DataSetLoader<Task> tasksLoader = (DataSetLoader<Task>) pluginFactory.getPlugin(DataSetLoader.class);
        return tasksLoader.loadSet(Task.class);
    }
    
    public Set<Member> getMembers() {
        PluginFactory pluginFactory = new PluginFactory(properties);
         
        // es un poco sucio...
        DataSetLoader<Member> membersLoader = (DataSetLoader<Member>) pluginFactory.getPlugin(DataSetLoader.class);
        return membersLoader.loadSet(Member.class);
    }
    
}

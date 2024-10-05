package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class TASkOcupadoFactory {
    private Properties properties;
    
    // ojo esto, ver qué recibe
    public TASkOcupadoFactory(String propertiesPath) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesPath));
        } catch (IOException e) {
            System.out.println("?TASkOcupadoFactory not working: I/O error");
        }
    }
    
    public Set<Observer> getObservers() {
       Discoverer<Observer> discoverer = new Discoverer(CoreSettings.EXTENSIONS);
       return discoverer.discover();
    }
    
    // FIXME: codigo repetido
    public Set<Task> getTasks() {
        PluginFactory pluginFactory = new PluginFactory(CoreSettings.PROPERTIES_FILE);
         
        // es un poco sucio...
        DataSetLoader<Task> tasksLoader = (DataSetLoader<Task>) pluginFactory.getPlugin(DataSetLoader.class);
        return tasksLoader.loadSet();
    }
    
    public Set<Member> getMembers() {
        PluginFactory pluginFactory = new PluginFactory(CoreSettings.PROPERTIES_FILE);
         
        // es un poco sucio...
        DataSetLoader<Member> membersLoader = (DataSetLoader<Member>) pluginFactory.getPlugin(DataSetLoader.class);
        return membersLoader.loadSet();
    }
    
}

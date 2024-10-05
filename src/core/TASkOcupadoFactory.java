package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
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
    
    
    public Set<Task> getTasks() {
         PluginFactory<DataSetLoader> puginFactory = new PluginFactory(CoreSettings.PROPERTIES_FILE);
         
         // FIXME: esto está claro que así no es
         //return pluginFactory.getPlugin().loadSet();
         return new HashSet<Task>();
        
    }
    
    public Set<Member> getMembers() {
        return new HashSet<Member>();
    }
    
}

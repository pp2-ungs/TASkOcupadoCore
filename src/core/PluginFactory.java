package core;

import java.io.FileInputStream;
import java.util.Properties;

public class PluginFactory {

    public static DataLoader loadInstance() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(AppSettings.RESOURCES_DIR + "config.properties")) {
            prop.load(fis);
            String loaderType = prop.getProperty("dataLoader");

            if (loaderType.equals("json")) 
            	return new JSONLoader();
            else if (loaderType.equals("calendar")) 
            	return new CalendarLoader();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new NullDataLoader();
    }
}

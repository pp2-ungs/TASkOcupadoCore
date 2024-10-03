package core;

import java.io.File;

// DONE
public class CoreSettings {

    public static final String HOME = System.getProperty("user.home");
    public static final String SLASH = File.separator;
    public static final String CONFIG = HOME + SLASH + ".TASkOcupado" + SLASH;
    public static final String RESOURCES = CONFIG + "Resources" + SLASH;
    public static final String EXTENSIONS = CONFIG + "Extensions" + SLASH;
    public static final String PROPERTIES_FILE = RESOURCES + "config.properties";

}

package core;

import java.io.File;

public class AppSettings {

    public static final String HOME = System.getProperty("user.home");
    public static final String SLASH = File.separator;
    public static final String TASKOCUPADO_DIR = HOME + SLASH + "TASkOcupado" + SLASH; // Todos los recursos de la app deberían ir en su directorio.
    
    public static final String TASKOCUPADO_CORE_DIR = TASKOCUPADO_DIR + "TASkOcupadoCore" + SLASH;
    public static final String RESOURCES_DIR = TASKOCUPADO_CORE_DIR + "resources" + SLASH;
    
    public static final String TASKOCUPADO_EXT_DIR = TASKOCUPADO_DIR + "TASkOcupadoExt" + SLASH;
    public static final String TASKOCUPADO_EXT_DIR_DIST = AppSettings.TASKOCUPADO_EXT_DIR + "dist" + SLASH;
}

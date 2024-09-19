package core;

public class PluginFactory {

    public static DataLoader loadInstances() {
        return new JSONLoader();
    }

    // TODO: leer archivo de configuración
}

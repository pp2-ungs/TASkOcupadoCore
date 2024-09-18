package plugin;

public class PluginFactory {
	
	public static DataLoader loadInstances() {
		return new JsonMock();
	}
	
	// TODO: leer archivo de configuración
}

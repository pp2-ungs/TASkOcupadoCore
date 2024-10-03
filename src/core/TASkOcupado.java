package core;

import java.util.Set;
import java.util.stream.Collectors;

// ???
// Javier dijo algo de Façade y de Adapter en el audio? No se oye bien.
public class TASkOcupado {
    
    // ??? Dijo Javier que acá no debería tener nada?
    private TaskAssigner taskAssigner;
    private CoreRepository data;
    
    public TASkOcupado() {
        init();
    }

    public void init() {
        // Elegir implementación
        PluginFactory plugin = new PluginFactory(CoreSettings.PROPERTIES_FILE);
        DataSetLoader loader = (DataSetLoader) plugin.getPlugin(DataSetLoader.class);

        // Cargar datos
        CoreRepository repository = new CoreRepository(loader);

        // Add observers
        Set<Object> objects = Discoverer.discover(CoreSettings.EXTENSIONS, Observer.class);
            Set<Observer> observers = objects.stream()
                .map(obj -> (Observer) obj)
                .collect(Collectors.toSet());

        // Create model
        TaskAssigner taskAssigner = new TaskAssigner(observers);

        // Estas dos líneas están para que esto siga compilando.
        // Estas dos líneas no deberían estar, el init() debería devolver el
        // modelo. Aparentemente, el modelo debería ser el taskAssigner.
        this.taskAssigner = taskAssigner; // remove me
        this.data = repository; // remove me
    }

    public void assignTask(String task, String member) {
        Task assignedTask = data.obtainTask(task);
        Member assignated = data.obtainMember(member);
        taskAssigner.assignTask(assignedTask, assignated);
    }

    public void addObserverToAssigner(Observer observer) {
        taskAssigner.addObserver(observer);
    }

    public String[] obtainMembers() {
        return data.getMembers();
    }

    public String[] obtainTasks() {
        return data.getTasks();
    }

}

package core;

import java.util.Set;
import java.util.stream.Collectors;

// ???
// Javier dijo algo de Façade y de Adapter en el audio? No se oye bien.

// Responsabilidad: inicializar el Core.
public class TASkOcupado {
    
    // ??? Dijo Javier que acá no debería tener nada?
    // X: si, solo crea y muere
    private TaskAssigner taskAssigner;
    private CoreData data;
    
    public TASkOcupado() {
        init();
    }

    // WIP
    public void init() {
        // Elegir implementación
        PluginFactory plugin = new PluginFactory(CoreSettings.PROPERTIES_FILE);
        DataSetLoader loader = (DataSetLoader) plugin.getPlugin(DataSetLoader.class);

        // Cargar datos
        CoreData coreData = new CoreData(loader);
        // var tasks = loader.loadSet(Task.class);
        // var members = loader.loadSet(Member.class);

        // Add observers
        Set<Object> objects = Discoverer.discover(CoreSettings.EXTENSIONS, Observer.class);
            Set<Observer> observers = objects.stream()
                .map(obj -> (Observer) obj)
                .collect(Collectors.toSet());

        // Create model
        TaskAssignment taskAssignment = new TaskAssignment();
        TaskAssigner taskAssigner = new TaskAssigner(taskAssignment, observers);

        // Estas dos líneas están para que esto siga compilando.
        // Estas dos líneas no deberían estar, el init() debería devolver el
        // modelo. Aparentemente, el modelo debería ser el taskAssigner.
        this.taskAssigner = taskAssigner; // remove me
        this.data = coreData; // remove me
        //return coso/adapter;
    }


    // \begin{remove}
    // Estas cosas las debería exponer el Adapter/Coso.
    public void assignTask(String task, String member) {
        Task assignedTask = data.obtainTask(task);
        Member assignated = data.obtainMember(member);
        taskAssigner.assignTask(assignedTask, assignated);
    }

    // ???
    // Esto serviría para la app externa que tiene que escuchar a nuestra app
    // principal.
    public void addObserverToAssigner(Observer observer) {
        taskAssigner.addObserver(observer);
    }

    public String[] obtainMembers() {
        return data.getMembers();
    }

    public String[] obtainTasks() {
        return data.getTasks();
    }
    // \end{remove}

}

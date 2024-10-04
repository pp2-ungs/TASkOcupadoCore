package core;

import java.util.Set;
import java.util.stream.Collectors;

// Responsabilidad: inicializar el Core.
public class TASkOcupado {

    public TaskAssignerAdapter init() {
        // \begin{Load observers}
        Set<Object> objects = Discoverer.discover(CoreSettings.EXTENSIONS, Observer.class);
        Set<Observer> observers = objects.stream()
            .map(obj -> (Observer) obj)
            .collect(Collectors.toSet());
        // \end{Load observers}

        // \begin{Create model}
        TaskAssignment taskAssignment = new TaskAssignment();
        TaskAssigner taskAssigner = new TaskAssigner(taskAssignment, observers);
        // \end{Create model}

        // \begin{Elegir implementación de los datos a cargar}
        PluginFactory plugin = new PluginFactory(CoreSettings.PROPERTIES_FILE);
        DataSetLoader loader = (DataSetLoader) plugin.getPlugin(DataSetLoader.class);
        // \end{Elegir implementación de los datos a cargar}

        // \begin{Cargar datos}
        CoreData coreData = new CoreData(loader);
        // var tasks = loader.loadSet(Task.class);
        // var members = loader.loadSet(Member.class);
        // \end{Cargar datos}

        // return Adapter
        return new TaskAssignerAdapter(taskAssigner, coreData);
    }

}

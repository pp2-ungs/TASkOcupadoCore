/*
class TASkOcupado {

    // Esto ya está en el init() de TASkOcupado, lo que hay que ver es sólo qué
    // devuelve.

    // X: acá me concentré en la idea del Adapter. lo que importa es el Adapter,
    // y puse todo para que se entienda bien la idea. A parte, fijate que no
    // está completamente así...
    public TaskAssignerAdapter init() {
        Set<Observer> observers = Discoverer.discover(...);
        TaskAssignment taskAssignment = new TaskAssignment();

        TaskAssigner taskAssigner = new TaskAssigner(observers, taskAssignment);

        DataSetLoader data = PluginFactory.getPlugin(...);  // no me convence el nombre de la clase
        Repository allResources = new Repository(data); // ? check si esto está okay. podría ser uno por tipo de recurso

        TaskAssignerAdapter adapter = new TaskAssignerAdapter(taskAssigner, allResources);
        return adapter;
    }
}

// esto no se llamaría así
// H: y si esto tendría un taskAssigner, un Set<Member> y un Set<Task>? aunque con
// eso volveríamos al comienzo cuando teníamos las tres cosas en TASkOcupado.
class TaskAssignerAdapter {
    TaskAssigner taskAssigner;
    Repository allResources; // o uno por tipo de resource

    // para mí esto es boolean
    public boolean assignTask(String task, String member) {
        Task t = allResources.Search(new Criteria().equals(Task.Description, task));  
        Member m = allResources.Search(new Criteria().equals(Member.name, member)); // name / email, lo que sea

        return taskAssigner.assignTask(t, m);
    }

    public String[] getTasks() {
      return allResources.Search(new Criteria().all());
    }

    public String[] getMembers() {
      return allResources.Search(new Criteria().all());  // esto queda raro
    }
}

// Esto ya está así en la UI

// Vuelvo a lo que dije arriba: la idea era mostrar cómo anda con el adapter :D
// si te fijas, ahora tiene un objeto TaskAssignerAdapter, no un TaskAssigner
class UI {
  TaskAssignerAdapter adapter;
  
  public void init() {
      adapter = new TASkOcupado().init();  // no se por qué esto no podría ser static
  }
}

*/

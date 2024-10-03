/*
class TASkOcupado {

    // H: para mí el init() debería devolver el coso que tenga adentro lo
    // observable y todos los datos.
    public Coso init() {
        Set<Observer> observers = Discoverer.discover(...);
        TaskAssignment taskAssignment = new TaskAssignment();

        TaskAssigner taskAssigner = new TaskAssigner(observers, taskAssignment);

        DataSetLoader data = PluginFactory.getPlugin(...);  // no me convence el nombre de la clase
        Repository allResources = new Repository(data); // ? check si esto está okay. podría ser uno por tipo de recurso

        TaskAssignerAdapter adapter = new TaskAssignerAdapter(taskAssigner, allResources);
        return Coso;
    }
}

// La diferencia de coso o TaskAssignerAdapter solo son los datos.
// No sé si me cierran los datos sueltos.
public class Coso {
    private TaskAssigner taskAssigner; // el observable
    private Set<Task> tasks; // todas las tasks
    private Set<Member> members; // todos los members
    
    // Métodos para que los use la UI

    public String[] obtainMembers() {
        return data.getMembers();
    }

    public String[] obtainTasks() {
        return data.getTasks();
    }

    public Task obtainTask(String taskDescription) {
        return tasks.stream()
                .filter(t -> t.getDescription().equals(taskDescription))
                .findFirst()
                .orElse(null);
    }

    public Member obtainMember(String name) {
        return members.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public String[] getTasks() {
        return tasks.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }

    public String[] getMembers() {
        return members.stream()
                .map(Member::toString)
                .toArray(String[]::new);
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

class UI {
  TaskAssignerAdapter adapter;
  
  public void init() {
      adapter = new TASkOcupado().init();  // no se por qué esto no podría ser static
  }
}

*/

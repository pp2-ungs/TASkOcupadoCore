class TASkOcupado {

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

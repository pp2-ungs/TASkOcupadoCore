package core;

// Adapter que expone el modelo a la UI. Esta clase tiene el modelo adentro.
// 
// Responsabilidad: adaptar el modelo para clientes externos.
public class TaskAssignerAdapter {

    // La clase que tiene la responsabilidad de la funcionalidad del modelo.
    private TaskAssigner taskAssigner;

    // La clase que tiene la responsabilidad de los datos del modelo.
    private CoreData coreData;

    public TaskAssignerAdapter(TaskAssigner taskAssigner, CoreData coreData) {
        this.taskAssigner = taskAssigner;
        this.coreData = coreData;
    }

    public void assignTask(String task, String member) {
        Task assignedTask = coreData.obtainTask(task);
        Member assignated = coreData.obtainMember(member);
        taskAssigner.assignTask(assignedTask, assignated);
    }

    // ??? Esto serviría para la app externa que tiene que escuchar a nuestra
    //     aplicación principal.
    public void addObserver(Observer observer) {
        taskAssigner.addObserver(observer);
    }

    public String[] obtainMembers() {
        return coreData.getMembers();
    }

    public String[] obtainTasks() {
        return coreData.getTasks();
    }
    
}

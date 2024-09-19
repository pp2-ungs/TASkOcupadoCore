package core;

public class TASkOcupado {

    private TaskAssigner taskAssigner;
    private Data data;

    // TODO: IMPORTANTE definir: hacemos un getTaskAssigner()
    // o le seteamos un task assigner?
    public TASkOcupado() {
        taskAssigner = new TaskAssigner();
        data = new Data();
    }

    // FIXME Se usa?
    public TaskAssigner getTaskAssigner() {
        return taskAssigner;
    }

    public void assignTask(String task, String member) {
        Task assignedTask = data.obtainTask(task);
        Member assignated = data.obtainMember(member);

        taskAssigner.assignTask(assignedTask, assignated);
    }

    public void addObserverToAssigner(Observer obserber) {
        taskAssigner.addObserver(obserber);
    }

    // FIXME estos dos no son getters
    public String[] getMembers() {
        return (String[]) data.getMembers();
    }

    public String[] getTasks() {
        return (String[]) data.getTasks();
    }

}

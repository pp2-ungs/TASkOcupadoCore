package core;

public class TASkOcupado {
    
    private TaskAssigner taskAssigner;
    private Data data;
    
    public TASkOcupado() {
        taskAssigner = new TaskAssigner();
        data = new Data();
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

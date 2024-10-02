package core;

// FIXME
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

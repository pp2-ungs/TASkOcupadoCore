package core;

import java.util.Set;

// ???
// Preguntar: si esto es el repo de los datos, podría ser ó debería ser un
// Singleton? Javier dice que Singleton es el anti-pattern. Consultarle.
//
// Responsabilidad: brindar los datos del modelo a quien lo solicite.
public class CoreRepository {

    private Set<Task> tasks;
    private Set<Member> members;

    public CoreRepository(DataSetLoader loader) {
        tasks = loader.loadSet(Task.class);
        members = loader.loadSet(Member.class);
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

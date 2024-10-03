package core;

import java.util.Set;

// ???
// Preguntar: si esto es el repo de los datos, podría ser ó debería ser un
// Singleton? Javier dice que Singleton es el anti-pattern. Consultarle.
// X: podría ser... no veo por qué no, la verdad
//
// Argumentos pro singleton:
// - Una vez cargados en memoria, en nuestra app, ni los members, ni las tasks
//   cambian, es decir, podrían ser inmutables.
// - Como los datos no cambian, con una sola instancia en memora se puede
//   acceder a los desde distintos puntos de la app.
//
// Esta clase sería una especie de wrapper que tendría la responsabilidad de
// brindar los mecanísmos de busqueda, y de obtención de los datos del modelo.
//
// Responsabilidad: brindar los datos del modelo a quien lo solicite.
//
// Hacer esto como un generic T.
public class CoreData {

    private Set<Task> tasks;
    private Set<Member> members;

    public CoreData(DataSetLoader loader) {
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

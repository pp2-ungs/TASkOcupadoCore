package core;

public class Main {

    public static void main(String[] args) {
        TASkOcupado taskOcupado = new TASkOcupado();

        String[] members = taskOcupado.getMembers();
        String[] tasks = taskOcupado.getTasks();

        taskOcupado.assignTask(tasks[0], members[0]);
        // Observaciones:
        //
        // 1. Core no hace nada, sólo asigna tareas. Si queremos que core
        //    notifique, se puede agregar un GenericNotificator, ó algo así.
        //
        // 2. UI usa core y notifica a la view. También usa Ext y notifica por
        //    email (mock).
        //
        // 3. Ext no hace nada, sólo tiene los notificadores.
    }
}

package core;

// FIXME
public class Main {

    public static void main(String[] args) {
        TASkOcupado taskOcupado = new TASkOcupado();

        String[] members = taskOcupado.obtainMembers();
        String[] tasks = taskOcupado.obtainTasks();

        System.out.print("Tasks: ");
        for (String task : tasks) {
            System.out.print(task + ", ");
        }

        System.out.print("\nMembers: ");
        for (String member : members) {
            System.out.print(member + ", ");
        }

    }

}

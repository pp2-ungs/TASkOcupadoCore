package core;

public class Main {

    public static void main(String[] args) {
        TASkOcupado taskOcupado = new TASkOcupado();

        String[] members = taskOcupado.obtainMembers();
        String[] tasks = taskOcupado.obtainTasks();

        System.out.println("Tasks:");
        for (String task : tasks) {
            System.out.println(task);
        }

        System.out.println("Members:");
        for (String member : members) {
            System.out.println(member);
        }

    }

}

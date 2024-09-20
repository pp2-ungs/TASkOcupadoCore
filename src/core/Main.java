package core;

public class Main {

    public static void main(String[] args) {
        TASkOcupado taskOcupado = new TASkOcupado();

        String[] members = taskOcupado.obtainMembers();
        String[] tasks = taskOcupado.obtainTasks();

        for (String member : members) {
            System.out.println(member);
        }

        for (String task : tasks) {
            System.out.println(task);
        }
        
    }
}

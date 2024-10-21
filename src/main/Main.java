package main;

import core.CoreFactory;
import core.Member;
import core.TASkOcupado;
import core.Task;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CoreFactory coreFactory = new CoreFactory("");
        TASkOcupado taskOcupado = coreFactory.create();
        
        Set<Task> tasks = taskOcupado.getTasks();
        Set<Member> members = taskOcupado.getMembers();
        
        System.out.println(tasks);
        System.out.println(members);
        
        taskOcupado.assignTask(new Task("Ir al gym"), new Member("Ximena Ebertz"));
    }
}

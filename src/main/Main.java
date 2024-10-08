package main;

import core.Member;
import core.TASkOcupado;
import core.Task;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        TASkOcupado taskOcupado = new TASkOcupado("");
        
        Set<Task> tasks = taskOcupado.getTasks();
        Set<Member> members = taskOcupado.getMembers();
        
        System.out.println(tasks);
        System.out.println(members);
    }
}

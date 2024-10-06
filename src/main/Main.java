package main;

import core.Member;
import core.TASkOcupado;
import core.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        TASkOcupado t = new TASkOcupado("");
        
        Set<Task> tasks = t.getTasks();
        Set<Member> members = t.getMembers();
        
        System.out.println(tasks);
        System.out.println(members);
        
        List<Task> taskList = new ArrayList<>(tasks);
        
        for(Member m : members) {
            if (m.getName().equals("Ximena Ebertz")) {
                t.assignTask(taskList.get(0), m);
            }
        }
    }
}

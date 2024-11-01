package main;

import core.CoreFactory;
import core.Person;
import core.TASkOcupado;
import core.Task;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CoreFactory coreFactory = new CoreFactory("");
        TASkOcupado taskOcupado = coreFactory.create();
        
        Set<Task> tasks = taskOcupado.getTasks();
        Set<Person> members = taskOcupado.getMembers();
        
        System.out.println(tasks);
        System.out.println(members);
    }
}

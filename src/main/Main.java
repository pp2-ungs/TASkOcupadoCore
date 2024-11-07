package main;

import core.TASkOcupadoFactory;
import core.Person;
import core.TASkOcupado;
import core.Task;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        TASkOcupado taskOcupado = new TASkOcupadoFactory("").create();
        
        Set<Task> tasks = taskOcupado.getTasks();
        Set<Person> members = taskOcupado.getPeople();
        
        System.out.println(tasks);
        System.out.println(members);
    }

}

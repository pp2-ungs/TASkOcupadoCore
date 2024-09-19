package plugin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import core.Member;
import core.Task;


public class JsonMock implements DataLoader {

	@Override
	public Set<Member> loadMembers() {
		FileReader jsonReader;
		try {
			jsonReader = new FileReader("resources/members.json");
			
			Gson gson = new Gson();
			
            Type memberSetType = new TypeToken<Set<Member>>() {}.getType();
            Set<Member> members = gson.fromJson(jsonReader, memberSetType);

            return members;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HashSet<>();
	}

	@Override
	public Set<Task> loadTasks() {
		FileReader jsonReader;
		try {
			jsonReader = new FileReader("resources/tasks.json");
			Gson gson = new Gson();
			
            Type taskSetType = new TypeToken<Set<Task>>() {}.getType();
            Set<Task> tasks = gson.fromJson(jsonReader, taskSetType);

            return tasks; // Devuelve el conjunto de miembros
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HashSet<>();
	}

}

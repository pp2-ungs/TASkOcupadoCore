package plugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.Member;
import core.Task;

public class JsonMock implements DataLoader {

	@Override
	public Set<Member> loadMembers() {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("resources/members.json");
		        InputStreamReader jsonReader = new InputStreamReader(inputStream)) {
			
			Gson gson = new Gson();
			
            Type memberSetType = new TypeToken<Set<Member>>() {}.getType();
            Set<Member> members = gson.fromJson(jsonReader, memberSetType);

            return members;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HashSet<>();
	}

	@Override
	public Set<Task> loadTasks() {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("resources/tasks.json");
		        InputStreamReader jsonReader = new InputStreamReader(inputStream)) {
			Gson gson = new Gson();
			
            Type taskSetType = new TypeToken<Set<Task>>() {}.getType();
            Set<Task> tasks = gson.fromJson(jsonReader, taskSetType);

            return tasks;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HashSet<>();
	}

}

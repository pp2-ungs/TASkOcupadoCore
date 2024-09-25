package core;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONLoader implements DataLoader {

    @Override
    public Set<Member> loadMembers() {
        try {
            Gson gson = new Gson();

            Type memberSetType = new TypeToken<Set<Member>>() {
            }.getType();

            Set<Member> members = gson.fromJson(
                    new JsonReader(new FileReader(CoreSettings.RESOURCES_DIR + "members.json")),
                    memberSetType
            );
            return members;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new HashSet<>();
    }

    @Override
    public Set<Task> loadTasks() {
        try {
            Gson gson = new Gson();

            Type taskSetType = new TypeToken<Set<Task>>() {
            }.getType();
            Set<Task> tasks = gson.fromJson(
                    new JsonReader(new FileReader(CoreSettings.RESOURCES_DIR + "tasks.json")),
                    taskSetType
            );

            return tasks;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new HashSet<>();
    }

}

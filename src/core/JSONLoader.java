package core;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONLoader implements DataLoader {

    public static final String HOME = System.getProperty("user.home");
    public static final String SLASH = File.separator;
    public static final String RESOURCES_DIR = HOME + SLASH + "resources" + SLASH;

    @Override
    public Set<Member> loadMembers() {
        try {
            Gson gson = new Gson();

            Type memberSetType = new TypeToken<Set<Member>>() {
            }.getType();

            Set<Member> members = gson.fromJson(
                    new JsonReader(new FileReader(RESOURCES_DIR + "members.json")),
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
                    new JsonReader(new FileReader(RESOURCES_DIR + "tasks.json")),
                    taskSetType
            );

            return tasks;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new HashSet<>();
    }

}

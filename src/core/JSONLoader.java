package core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

// DONE
// JSONLoader no sabe nada, sólo sabe que tiene que cargar archivos .json.
public class JSONLoader<T> implements DataSetLoader {

    @Override
    public Set<T> loadSet(Class classObject) {
        Gson gson = new Gson();
        String fileName = CoreSettings.RESOURCES + classObject.getSimpleName() + ".json";
        Type dataSetType = TypeToken.getParameterized(Set.class, classObject).getType();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("?" + fileName + " not found");
            System.out.println("?using empty set");
            return new HashSet<>();
        }

        return gson.fromJson(new JsonReader(fileReader), dataSetType);
    }

}

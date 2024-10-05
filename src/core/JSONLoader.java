package core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

// DONE
// JSONLoader no sabe nada, sólo sabe que tiene que cargar archivos .json.
//
// ???
// Está hardcodeado el path de RESOURCES, aunque esto es una implementación de
// testing únicamente.
// X: esta clase se supone que tiene que ir en Resources como un .class
// por ahí no hace falta que hardcodee el path de RESOURCES
public class JSONLoader<T> implements DataSetLoader {
    
    public JSONLoader() {
        
    }

    @Override
    public Set<T> loadSet() {
        Gson gson = new Gson();
        String fileName = CoreSettings.RESOURCES + getGenericType().getSimpleName() + ".json";
        Type dataSetType = TypeToken.getParameterized(Set.class, getGenericType()).getType();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            // FIXME: prints?
            
            System.out.println("?" + fileName + " not found");
            System.out.println("?using empty set");
            return new HashSet<>();
        }

        return gson.fromJson(new JsonReader(fileReader), dataSetType);
    }
    
    // FIXME: codigo repetido en PluginFactory
    private Class<?> getGenericType() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            Type[] typeArguments = ((ParameterizedType) superclass).getActualTypeArguments();
            return (Class<?>) typeArguments[0];
        }
        throw new RuntimeException("Cannot resolve generic type");
    }

}

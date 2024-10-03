package core;

import java.util.HashSet;
import java.util.Set;

// ??? Esta clase queda, ó dijo Javier que hay que sacarla?
// X: dijo Javier que se va nomás
public class NullDataLoader<T> implements DataSetLoader {
    
    @Override
    public Set<T> loadSet(Class classObject) {
        return new HashSet<>();
    }

    public boolean isNull() {
        return true;
    }

}

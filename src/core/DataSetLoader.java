package core;

import java.util.Set;

// DONE
// Responsabilidad: cargar data sets.
public interface DataSetLoader<T> {

    public Set<T> loadSet(Class<T> classObject);

}

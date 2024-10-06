package core;

import java.util.Set;

// DONE
// Responsabilidad: cargar data sets.
public interface ContentLoader<T> {

    public Set<T> loadSet(Class<T> classObject);

}

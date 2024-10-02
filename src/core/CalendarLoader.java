package core;

import java.util.HashSet;
import java.util.Set;

// DONE
// CalendarLoader no sabe nada, única responsabilidad cargar datos de calendarios.
public class CalendarLoader<T> implements DataSetLoader {

    @Override
    public Set<T> loadSet(Class classObject) {
        return new HashSet<>();
    }

}
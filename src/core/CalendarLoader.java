package core;

import java.util.HashSet;
import java.util.Set;

// DONE
// CalendarLoader no sabe nada, única responsabilidad cargar datos de calendarios.
// X: depende de como lo pensemos esto puede ser dos cosas, uno para Task y otro
// para Member. no me queda claro la verdad
public class CalendarLoader<T> implements DataSetLoader {

    @Override
    public Set<T> loadSet(Class classObject) {
        return new HashSet<>();
    }

}

package core;

import java.util.Set;

public interface ContentFactory<T> {

    public Set<T> createSetOf(Class<T> classObject);

}

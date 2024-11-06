package core;

import java.util.Set;

public interface ContentLoader<T> {

    public Set<T> createSetOf(Class<T> classObject);

}

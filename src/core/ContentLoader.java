package core;

import java.util.Set;

public interface ContentLoader {

    public <T> Set<T> loadSetOf(Class<T> classObject);

}

package tools.discovery;

import java.nio.file.Path;
import java.util.Set;

public interface ResourceParser {
    boolean supports(Path resource);
    <T> Set<Class<? extends T>> parse(Path resource, Path basePath, Class<T> type);
}

package tools.discovery;

import java.nio.file.Path;
import java.util.List;

public interface ResourceFinder {
    List<Path> findResources(Path basePath);
}

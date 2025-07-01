package tools.discovery;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Discoverer {

    private final ResourceFinder resourceFinder;
    private final List<ResourceParser> resourceParsers;

    public Discoverer(ResourceFinder resourceFinder, List<ResourceParser> resourceParsers) {
        this.resourceFinder = resourceFinder;
        this.resourceParsers = resourceParsers;
    }

    public <T> Set<Class<? extends T>> discover(String basePath, Class<T> type) {
        Set<Class<? extends T>> foundClasses = new HashSet<>();
        List<Path> resources = resourceFinder.findResources(Paths.get(basePath));

        for (Path resource : resources) {
            for (ResourceParser parser : resourceParsers) {
                if (parser.supports(resource)) {
                    foundClasses.addAll(parser.parse(resource, type));
                    break; // Un solo parser por recurso
                }
            }
        }
        return foundClasses;
    }
}

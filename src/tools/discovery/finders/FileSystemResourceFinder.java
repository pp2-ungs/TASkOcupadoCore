package tools.discovery.finders;

import tools.discovery.ResourceFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystemResourceFinder implements ResourceFinder {
    @Override
    public List<Path> findResources(Path basePath) {
        if (!Files.exists(basePath)) {
            return List.of();
        }
        try (Stream<Path> stream = Files.walk(basePath)) {
            return stream.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error al recorrer el directorio: " + basePath);
            return List.of();
        }
    }
}
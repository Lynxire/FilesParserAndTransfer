package data;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FilesReader {
    public static List<String> filesRead(String path) throws IOException {
        Stream<Path> paths = Files.walk(Path.of(path));
        List<String> stringList = paths.filter(Files::isRegularFile)
                .flatMap(file -> {
                    try {
                        return Files.lines(file);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .toList();

        return stringList;
    }
}

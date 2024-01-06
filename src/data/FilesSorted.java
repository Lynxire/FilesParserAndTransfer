package data;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FilesSorted {


    public static void sorted() throws IOException {
        String path = "C:\\Users\\fined\\IdeaProjects\\FilesParserAndTransfer\\src\\files\\source_files\\";
        for (int i = 0; i < FilesReader.filesRead(path).size(); i++) {
            String[] strings = FilesReader.filesRead(path).get(i).split(" ");
            Path file = Files.createFile(Path.of("C:\\Users\\fined\\IdeaProjects\\FilesParserAndTransfer\\src\\files\\sorted_files\\" + i + ".txt"));
            Files.writeString(file, strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3] + " " + strings[4]);
        }
    }
}

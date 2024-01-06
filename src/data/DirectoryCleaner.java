package data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DirectoryCleaner {

    public static void clearDirectory(String path, String name) throws IOException {

        List<Boolean> list = Files.walk(Path.of(path))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .map(File::delete)
                .toList();
            if (!list.isEmpty()) {
                System.out.println("директория " + name + " очищена");

            }



    }


}

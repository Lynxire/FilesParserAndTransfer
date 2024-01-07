package data;

import exception.FaildCount;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FilesSorted {


    public static void sorted() throws IOException {

        String path = ".\\src\\files\\source_files\\";
        List<String> stringList = FilesReader.filesRead(path);
        try {
            DirectoryCleaner.clearDirectory(".\\src\\files\\sorted_files", "отсортированных файлов");
            if(stringList.isEmpty()){
                throw new FaildCount("Нету исходных файлов");
            }

            for (int i = 0; i < stringList.size(); i++) {
                String[] strings = stringList.get(i).split(" ");
                Path file = Files.createFile(Path.of(".\\src\\files\\sorted_files\\" + i + ".txt"));
                Files.writeString(file, strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3] + " " + strings[4]);
                System.out.println("Файл - " + i +" отсортирован");
                ListLog.arrayList.add("Файл - " + i +" отсортирован");
            }
        }
        catch (FaildCount e){
            System.out.println(e.getMessage());
            ListLog.arrayList.add(e.getMessage());
        }

    }
}

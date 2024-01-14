package data;

import exception.FailCount;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FilesSorted {


    public static void sorted() throws IOException {



        List<String> stringList = FilesReader.filesRead(Files_Path.PATH_SOURCE);
        try {
            DirectoryCleaner.clearDirectory(Files_Path.PATH_SORTED_FILES, "отсортированных файлов");
            if(stringList.isEmpty()){
                throw new FailCount(ExceptionMessage.FAIL_SOURCE_FILE);
            }

            for (int i = 0; i < stringList.size(); i++) {
                int j = i+1;
                String[] strings = stringList.get(i).split(" ");
                Path file = Files.createFile(Path.of(Files_Path.PATH_SORTED_FILES + j + ".txt"));
                Files.writeString(file, strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3] + " " + strings[4]);
                System.out.println("Файл - " + j +" отсортирован");
                ListLog.arrayList.add("Файл - " + j +" отсортирован");
            }
        }
        catch (FailCount e){
            System.out.println(e.getMessage());
            ListLog.arrayList.add(e.getMessage());
        }

    }
}

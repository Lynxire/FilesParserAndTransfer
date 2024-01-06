package model;

import data.FilesReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransferMoney {
   public static String pathSortedFiles = "C:\\Users\\fined\\IdeaProjects\\FilesParserAndTransfer\\src\\files\\sorted_files\\";

    public static void transferMoney() throws IOException {
        List<String> filesRead = FilesReader.filesRead(pathSortedFiles);
        for (int i = 0; i < filesRead.size(); i++) {
            String[] strings = filesRead.get(i).split(" ");
            Path file = Files.createFile(Path.of("C:\\Users\\fined\\IdeaProjects\\FilesParserAndTransfer\\src\\files\\result\\" + "files_" + i + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "-good.txt"));
            int i1 = Integer.parseInt(strings[1]) - Integer.parseInt(strings[4]);
            int i2 = Integer.parseInt(strings[3]) + Integer.parseInt(strings[4]);
            Files.writeString(file,strings[0] + " " + i1 + " " + strings[2] + " " + i2 + " " + strings[4]);

        }
    }

}

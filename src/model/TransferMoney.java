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
        try {
            if(filesRead.isEmpty()){
                throw new RuntimeException("Нету отсортированных файлов");
            }

            for (int i = 0; i < filesRead.size(); i++) {
                String[] strings = filesRead.get(i).split(" ");
                try {
                    if(Integer.parseInt(strings[1]) < Integer.parseInt(strings[4])){
                        throw new RuntimeException("На счете недостаточно денег для перевода");
                    }
//              if(strings[0].matches("") && strings[2].matches(""))
//                {
//                    throw new RuntimeException("Некорректный формат счета");
//                }
                    int i1 = Integer.parseInt(strings[1]) - Integer.parseInt(strings[4]);
                    int i2 = Integer.parseInt(strings[3]) + Integer.parseInt(strings[4]);
                    Path file = Files.createFile(Path.of("C:\\Users\\fined\\IdeaProjects\\FilesParserAndTransfer\\src\\files\\result\\" + "files_" + i + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "-good.txt"));
                    Files.writeString(file,strings[0] + " " + i1 + " " + strings[2] + " " + i2 + " " + strings[4]);
                }catch (RuntimeException e){
                    Path file = Files.createFile(Path.of("C:\\Users\\fined\\IdeaProjects\\FilesParserAndTransfer\\src\\files\\result\\" + "files_" + i + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "-bad.txt"));
                    Files.writeString(file,e.getMessage().toString());
                }

            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }

}

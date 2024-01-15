package model;

import data.*;
import exception.FailCount;
import exception.FailFormatNumber;
import exception.ReadFileNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransferMoney {


    public static void transferMoney() throws IOException {
        List<String> filesRead = FilesReader.filesRead(Files_Path.PATH_SORTED_FILES);
        try {
            if (filesRead.isEmpty()) {
                throw new ReadFileNull("Нету отсортированных файлов");
            }
            DirectoryCleaner.clearDirectory(Files_Path.PATH_RESULT, "результатов");
            for (int i = 0; i < filesRead.size(); i++) {
                int j = i + 1;
                String[] strings = filesRead.get(i).split(" ");
                try {
                    if (Integer.parseInt(strings[1]) < Integer.parseInt(strings[4])) {
                        throw new FailCount(ExceptionMessage.FAIL_COUNT);
                    }
                    if (!strings[0].matches("\\d{5}-\\d{5}") || !strings[2].matches("\\d{5}-\\d{5}")) {
                        throw new FailFormatNumber(ExceptionMessage.FAIL_FORMAT_NUMBER);
                    }
                    int i1 = Integer.parseInt(strings[1]) - Integer.parseInt(strings[4]);
                    int i2 = Integer.parseInt(strings[3]) + Integer.parseInt(strings[4]);
                    Path file = Files.createFile(Path.of(Files_Path.PATH_RESULT + "files_" + j + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "-good.txt"));
                    Files.writeString(file, strings[0] + " " + i1 + " " + strings[2] + " " + i2 + " " + strings[4]);
                    System.out.println("Перевод выполнен для файла - " + j);
                    ListLog.arrayList.add("Перевод выполнен для файла - " + j);

                } catch (FailCount | FailFormatNumber e) {
                    Path file = Files.createFile(Path.of(Files_Path.PATH_RESULT + "files_" + j + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "-bad.txt"));
                    Files.writeString(file, e.getMessage());
                    System.out.println("Перевод не выполнен для файла - " + j + " Причина - " + e.getMessage());
                    ListLog.arrayList.add("Перевод не выполнен для файла - " + j + " Причина - " + e.getMessage());
                }

            }
        } catch (ReadFileNull e) {
            System.out.println(e.getMessage());
            ListLog.arrayList.add(e.getMessage());

        }
    }
}

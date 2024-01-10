package model;

import data.DirectoryCleaner;
import data.FilesReader;
import data.ListLog;
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
    private static final String pathSortedFiles = ".\\src\\files\\sorted_files\\";
    private static final String pathResult = ".\\src\\files\\result\\";

    public static void transferMoney() throws IOException {
        List<String> filesRead = FilesReader.filesRead(pathSortedFiles);
        try {
            if (filesRead.isEmpty()) {
                throw new ReadFileNull("Нету отсортированных файлов");
            }
            DirectoryCleaner.clearDirectory(pathResult, "результатов");
            for (int i = 0; i < filesRead.size(); i++) {
                int j = i+1;
                String[] strings = filesRead.get(i).split(" ");
                try {
                    if (Integer.parseInt(strings[1]) < Integer.parseInt(strings[4])) {
                        throw new FailCount("На счете недостаточно денег для перевода");
                    }
                    if (!strings[0].matches("\\d{5}-\\d{5}") || !strings[2].matches("\\d{5}-\\d{5}")) {
                        throw new FailFormatNumber("Некорректный формат счета");
                    }
                    int i1 = Integer.parseInt(strings[1]) - Integer.parseInt(strings[4]);
                    int i2 = Integer.parseInt(strings[3]) + Integer.parseInt(strings[4]);
                    Path file = Files.createFile(Path.of(pathResult + "files_" + j + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "-good.txt"));
                    Files.writeString(file, strings[0] + " " + i1 + " " + strings[2] + " " + i2 + " " + strings[4]);
                    System.out.println("Перевод выполнен для файла - " + j);
                    ListLog.arrayList.add("Перевод выполнен для файла - " + j);

                } catch (FailCount | FailFormatNumber e) {
                    Path file = Files.createFile(Path.of(pathResult + "files_" + j + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "-bad.txt"));
                    Files.writeString(file, e.getMessage().toString());
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

package model;

import data.DirectoryCleaner;
import data.FilesReader;
import data.ListLog;
import exception.FaildCount;
import exception.FaildFormatNumber;
import exception.ReadFileNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransferMoney {
    public static String pathSortedFiles = ".\\src\\files\\sorted_files\\";

    public static void transferMoney() throws IOException {
        List<String> filesRead = FilesReader.filesRead(pathSortedFiles);
        try {
            if (filesRead.isEmpty()) {
                throw new ReadFileNull("Нету отсортированных файлов");
            }
            DirectoryCleaner.clearDirectory(".\\src\\files\\result", "результатов");
            for (int i = 0; i < filesRead.size(); i++) {
                String[] strings = filesRead.get(i).split(" ");
                try {
                    if (Integer.parseInt(strings[1]) < Integer.parseInt(strings[4])) {
                        throw new FaildCount("На счете недостаточно денег для перевода");
                    }
                    if (!strings[0].matches("\\d{5}-\\d{5}") || !strings[2].matches("\\d{5}-\\d{5}")) {
                        throw new FaildFormatNumber("Некорректный формат счета");
                    }
                    int i1 = Integer.parseInt(strings[1]) - Integer.parseInt(strings[4]);
                    int i2 = Integer.parseInt(strings[3]) + Integer.parseInt(strings[4]);
                    Path file = Files.createFile(Path.of(".\\src\\files\\result\\" + "files_" + i + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "-good.txt"));
                    Files.writeString(file, strings[0] + " " + i1 + " " + strings[2] + " " + i2 + " " + strings[4]);
                    System.out.println("Перевод выполнен для файла - " + i);
                    ListLog.arrayList.add("Перевод выполнен для файла - " + i);

                } catch (FaildCount | FaildFormatNumber e) {
                    Path file = Files.createFile(Path.of(".\\src\\files\\result\\" + "files_" + i + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "-bad.txt"));
                    Files.writeString(file, e.getMessage().toString());
                    System.out.println("Перевод не выполнен для файла - " + i + " Причина - " + e.getMessage());
                    ListLog.arrayList.add("Перевод не выполнен для файла - " + i + " Причина - " + e.getMessage());
                }

            }
        } catch (ReadFileNull e) {
            System.out.println(e.getMessage());
            ListLog.arrayList.add(e.getMessage());

        }

    }

}

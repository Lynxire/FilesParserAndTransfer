package model;

import data.FilesSorted;
import data.ListLog;

import java.io.IOException;
import java.util.Scanner;

public class UserUI {
    public static void userUiStart() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            System.out.println();
            System.out.println("---------------------");
            System.out.println("Выберите пункт:");
            System.out.println("1. Отсортировать файлы");
            System.out.println("2. Перевести деньги");
            System.out.println("3. Показать все выполненые операции");
            System.out.println("4. Выход");
            number = scanner.nextInt();
            switch (number) {
                case 1:
                    FilesSorted.sorted();
                    break;
                case 2:
                    TransferMoney.transferMoney();
                    break;
                case 3:
                    if(ListLog.arrayList.isEmpty()){
                        System.out.println("Еще не было выполнено никаких операций");
                    }
                    ListLog.arrayList.forEach(System.out::println);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте еще раз.");
            }
        } while (number != 4);


        }
    }


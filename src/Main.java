import data.FilesSorted;
import model.TransferMoney;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilesSorted.sorted();
        TransferMoney.transferMoney();
    }
}
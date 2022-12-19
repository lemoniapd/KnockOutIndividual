package KnockOut.Facade;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Write {

    PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("Dice results.txt", true)));

    public Write() throws IOException {
    }

    public void writeResultToFile(String result) {
        printWriter.println(result);
        printWriter.close();
    }
}

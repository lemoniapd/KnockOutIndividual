package KnockOut.Facade;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

    PrintWriter printWriter;

    public Writer() throws IOException {
    }

    public void writeResultToFile(String result) {
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter("Dice results.txt", true)));
            printWriter.println(result);
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearResultFile() {
        try {
            FileWriter fw = new FileWriter("Dice results.txt", false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception exception) {
            System.out.println("Exception has been caught");
        }
    }
}

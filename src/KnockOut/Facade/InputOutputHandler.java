package KnockOut.Facade;

import java.io.IOException;
import java.util.ArrayList;

public class InputOutputHandler {

    private final Writer OUT = new Writer();
    private final Reader IN = new Reader();

    public InputOutputHandler() throws IOException {
    }

    public void writeResultsToFile(String result) throws IOException {
        OUT.writeResultToFile(result);
    }

    public ArrayList<String> resultListFromFile() throws IOException {
        return IN.resultListFromFile();
    }

    public void clearResultFile(){
        OUT.clearResultFile();
    }
}

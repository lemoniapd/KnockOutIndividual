package KnockOut.Facade;

import java.io.IOException;
import java.util.ArrayList;

public class InputOutputHandler {

    private final Write OUT = new Write();
    private final Read IN = new Read();

    public InputOutputHandler() throws IOException {
    }

    public void writeResultsToFile(String result) throws IOException {
        OUT.writeResultToFile(result);
    }

    public ArrayList<String> resultListFromFile() throws IOException {
        return IN.resultListFromFile();
    }
}

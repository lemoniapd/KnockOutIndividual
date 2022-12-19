package KnockOut.Facade;

import java.io.*;
import java.util.ArrayList;

public class Reader {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("Dice results.txt")));

    public Reader() throws FileNotFoundException {
    }

    public ArrayList<String> resultListFromFile() throws IOException {
        ArrayList<String> resultList = new ArrayList<>();
        try {
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                resultList.add(nextLine);
            }
        } catch (Exception ex) {
            System.out.println("Något gick fel!");
        }
        return resultList;
    }
}

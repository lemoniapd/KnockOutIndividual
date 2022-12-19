package KnockOut;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public Main() {
        JOptionPane.showMessageDialog(null, """
                Välkommen till KnockOut!\s
                Reglerna är simpla:\s
                - Välj förbjudet nummer (2-12)\s
                - Kasta två tärningar tills summan av de två blir det förbjudna numret\s
                - Din poäng blir antalet rundor du lyckas ta dig igenom!\s
                
                
                Kontaktuppgifter till skapare: +46 8 123 55 55 info@noreply.se""");
        String name = JOptionPane.showInputDialog(null, "Ditt namn?");
        String s = JOptionPane.showInputDialog(null, "Förbjudet nummer?");
        int forbiddenNumber = Integer.parseInt(s.trim());
        try {
            new Game(name, forbiddenNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

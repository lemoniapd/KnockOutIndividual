package KnockOut;

import javax.swing.*;

public class Main {
    public Main() {
        JOptionPane.showMessageDialog(null, """
                Välkommen till KnockOut!\s
                Reglerna är simpla:\s
                - Välj x tärningar, 2-6 st.
                - Välj förbjudet nummer (x-6x)\s
                - Kasta tärningar tills summan av de blir det förbjudna numret\s
                - Din poäng blir antalet rundor du lyckas ta dig igenom!\s
                                
                                
                Kontaktuppgifter till skapare: +46 8 123 55 55 info@noreply.se""");
        try {
            String name = JOptionPane.showInputDialog(null, "Ditt namn?");
            String nr1 = JOptionPane.showInputDialog(null, "Hur många tärningar? (2-6 st)");
            int numberOfDice = Integer.parseInt(nr1.trim());
            String nr2 = JOptionPane.showInputDialog(null, "Förbjudet nummer?");
            int forbiddenNumber = Integer.parseInt(nr2.trim());
            new Game(name, numberOfDice, forbiddenNumber);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Du måste skriva något i rutan!");
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

package KnockOut;

import javax.swing.*;
import java.util.Random;

public abstract class Dice extends JFrame {
    protected int currentNumber = 1;

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void throwDice() {
        Random random = new Random();
        currentNumber = random.nextInt(1, 7);
    }


}

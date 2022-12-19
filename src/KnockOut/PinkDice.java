package KnockOut;

import javax.swing.*;

public class PinkDice extends Dice{

    private ImageIcon pinkImage1 = new ImageIcon("src/KnockOut/bilder/pinkDicePictures/dice1.png");
    private ImageIcon pinkImage2 = new ImageIcon("src/KnockOut/bilder/pinkDicePictures/dice2.png");
    private ImageIcon pinkImage3 = new ImageIcon("src/KnockOut/bilder/pinkDicePictures/dice3.png");
    private ImageIcon pinkImage4 = new ImageIcon("src/KnockOut/bilder/pinkDicePictures/dice4.png");
    private ImageIcon pinkImage5 = new ImageIcon("src/KnockOut/bilder/pinkDicePictures/dice5.png");
    private ImageIcon pinkImage6 = new ImageIcon("src/KnockOut/bilder/pinkDicePictures/dice6.png");
    private ImageIcon[] pinkDieFaces = {pinkImage1, pinkImage2, pinkImage3, pinkImage4, pinkImage5, pinkImage6};

    public ImageIcon getImage() {
        return pinkDieFaces[currentNumber - 1];
    }
}

package KnockOut.Factory;

import javax.swing.*;

public class YellowDice extends Dice {

    private ImageIcon yellowImage1 = new ImageIcon("src/KnockOut/bilder/yellowDicePictures/dice1.png");
    private ImageIcon yellowImage2 = new ImageIcon("src/KnockOut/bilder/yellowDicePictures/dice2.png");
    private ImageIcon yellowImage3 = new ImageIcon("src/KnockOut/bilder/yellowDicePictures/dice3.png");
    private ImageIcon yellowImage4 = new ImageIcon("src/KnockOut/bilder/yellowDicePictures/dice4.png");
    private ImageIcon yellowImage5 = new ImageIcon("src/KnockOut/bilder/yellowDicePictures/dice5.png");
    private ImageIcon yellowImage6 = new ImageIcon("src/KnockOut/bilder/yellowDicePictures/dice6.png");
    private ImageIcon[] yellowDieFaces = {yellowImage1, yellowImage2, yellowImage3, yellowImage4, yellowImage5, yellowImage6};

    @Override
    public ImageIcon getImage() {
        return yellowDieFaces[currentNumber - 1];
    }
}

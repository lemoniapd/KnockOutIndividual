package KnockOut;

import KnockOut.Facade.InputOutputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends JFrame implements ActionListener {
    private final int NUMBER_OF_DICE = 2;
    private int currentTotal;
    private int knockOutNumber;
    private String name;
    private int points = 0;
    PinkDice dice1 = new PinkDice();
    YellowDice dice2 = new YellowDice();
    InputOutputHandler inputOutputHandler = new InputOutputHandler();
    JPanel basePanel = new JPanel();
    JPanel topHalf = new JPanel();
    JPanel bottomHalf = new JPanel();
    JLabel title = new JLabel("KnockOut!");
    JLabel die1 = new JLabel(dice1.getImage());
    JLabel die2 = new JLabel(dice2.getImage());
    JLabel pointsLabel = new JLabel("Poäng: ");
    JLabel koNumber = new JLabel("KnockOut-nummer: " + knockOutNumber);
    JButton throwDice = new JButton("Kasta tärningar");
    JButton playAgain = new JButton("Spela igen");
    JButton clearScoreboard = new JButton("Rensa topplista");
    JLabel highscore1 = new JLabel();
    JLabel highscore2 = new JLabel();
    JLabel highscore3 = new JLabel();


    public Game(String name, int knockoutNumber) throws IOException {
        this.add(basePanel);
        basePanel.setLayout(new BorderLayout());
        basePanel.add(topHalf, BorderLayout.NORTH);
        basePanel.add(bottomHalf, BorderLayout.SOUTH);
        topHalf.setLayout(new GridLayout(4, 1));
        topHalf.add(title);
        topHalf.add(koNumber);
        topHalf.add(pointsLabel);
        topHalf.add(throwDice);
        throwDice.addActionListener(this);
        playAgain.addActionListener(this);
        clearScoreboard.addActionListener(this);
        title.setFont(new Font("Tahoma", Font.PLAIN, 25));
        bottomHalf.setLayout(new GridLayout(1, 2));
        bottomHalf.add(die1);
        bottomHalf.add(die2);
        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        if (knockoutNumber < NUMBER_OF_DICE || knockoutNumber > 6 * NUMBER_OF_DICE) {
            JOptionPane.showMessageDialog(null, "KnockOut-nummer måste vara mellan "
                    + NUMBER_OF_DICE + "-" + NUMBER_OF_DICE * 6 + "!");
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.knockOutNumber = knockoutNumber;
        koNumber.setText("KnockOut-nummer: " + knockoutNumber);
    }

    public boolean gameLost() {
        return currentTotal == knockOutNumber;
    }

    public ArrayList<String> scoreBoardList(ArrayList<String> resultList) {
        ArrayList<Integer> scores = new ArrayList<>();
        for (String result : resultList) {
            scores.add(Integer.parseInt(result.substring(0, result.indexOf(' '))));
        }
        Collections.sort(scores, Collections.reverseOrder());

        ArrayList<String> sortedResults = new ArrayList<>();
        for (Integer score : scores) {
            for (String result : resultList) {
                if (result.contains(String.valueOf(score) + " ")) {
                    sortedResults.add(result);
                }
            }
        }
        return sortedResults;
    }

    public void setHighscores(ArrayList<String> highscores) {
        if (highscores.size() > 0) {
            highscore1.setText(highscores.get(0));
        }
        if (highscores.size() > 1) {
            highscore2.setText(highscores.get(1));
        }
        if (highscores.size() > 2) {
            highscore3.setText(highscores.get(2));
        }
        topHalf.add(highscore1);
        topHalf.add(highscore2);
        topHalf.add(highscore3);
        revalidate();
        repaint();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == throwDice && !gameLost()) {
            dice1.throwDice();
            die1.setIcon(dice1.getImage());
            dice2.throwDice();
            die2.setIcon(dice2.getImage());
            currentTotal = dice1.getCurrentNumber() + dice2.getCurrentNumber();
            points++;
            pointsLabel.setText("Poäng: " + points);
            if (gameLost()) {
                title.setText("DU FÖRLORADE!");
                topHalf.remove(throwDice);
                topHalf.remove(koNumber);
                topHalf.remove(pointsLabel);
                bottomHalf.remove(die1);
                bottomHalf.remove(die2);
                bottomHalf.add(playAgain);
                bottomHalf.add(clearScoreboard);
                try {
                    inputOutputHandler.writeResultsToFile(points + " poäng, " + name);
                    setHighscores(scoreBoardList(inputOutputHandler.resultListFromFile()));
                    //inputOutputHandler.resultListFromFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource() == clearScoreboard) {
            inputOutputHandler.clearResultFile();
        } else if (e.getSource() == playAgain) {
            setVisible(false);
            revalidate();
            repaint();
            new Main();
        }
    }
}

package KnockOut;

import KnockOut.Facade.InputOutputHandler;
import KnockOut.Factory.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends JFrame implements ActionListener {
    private int currentTotal;
    private int knockOutNumber;
    private String name;
    private int points = 0;
    private int numberOfDice;
    InputOutputHandler inputOutputHandler = new InputOutputHandler();
    DiceFactory diceFactory = new DiceFactory();
    ArrayList<Dice> listOfDice = new ArrayList<>();
    ArrayList<JLabel> listOfDiceLabels = new ArrayList<>();
    JPanel basePanel = new JPanel();
    JPanel topHalf = new JPanel();
    JPanel bottomHalf = new JPanel();
    JLabel title = new JLabel("KnockOut!");
    JLabel pointsLabel = new JLabel("Poäng: ");
    JLabel koNumber = new JLabel("KnockOut-nummer: " + knockOutNumber);
    JButton throwDice = new JButton("Kasta tärningar");
    JButton playAgain = new JButton("Spela igen");
    JButton clearScoreboard = new JButton("Rensa topplista");
    JLabel highscore1 = new JLabel();
    JLabel highscore2 = new JLabel();
    JLabel highscore3 = new JLabel();

    public Game(String name, int numberOfDice, int knockoutNumber) throws IOException {
        this.numberOfDice = numberOfDice;
        this.name = name;
        this.knockOutNumber = knockoutNumber;
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
        bottomHalf.setLayout(new GridLayout(3, 2));
        populateListsOfDiceAndLabels(numberOfDice);
        for (JLabel labels : listOfDiceLabels) {
            bottomHalf.add(labels);
        }
        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        if (knockoutNumber < numberOfDice || knockoutNumber > 6 * numberOfDice) {
            JOptionPane.showMessageDialog(null, "KnockOut-nummer måste vara mellan "
                    + numberOfDice + "-" + numberOfDice * 6 + "!");
            throw new IllegalArgumentException();
        }

        koNumber.setText("KnockOut-nummer: " + knockoutNumber);
    }

    public boolean gameLost() {
        return currentTotal == knockOutNumber;
    }

    public void populateListsOfDiceAndLabels(int numberOfDice) {
        int toggle = 0;
        for (int i = 0; i < numberOfDice; i++) {
            if (toggle == 0) {
                listOfDice.add(diceFactory.getDie(DiceEnum.YELLOW_DICE));
                toggle = 1;
            } else {
                listOfDice.add(diceFactory.getDie(DiceEnum.PINK_DICE));
                toggle = 0;
            }
            listOfDiceLabels.add(new JLabel(listOfDice.get(i).getImage()));
        }
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
            for (Dice element : listOfDice) {
                element.throwDice();
            }
            for (int i = 0; i < listOfDiceLabels.size(); i++) {
                listOfDiceLabels.get(i).setIcon(listOfDice.get(i).getImage());
            }
            for (Dice element : listOfDice) {
                currentTotal += element.getCurrentNumber();
            }
            //currentTotal = dice1.getCurrentNumber() + dice2.getCurrentNumber();
            points++;
            pointsLabel.setText("Poäng: " + points);
            if (gameLost()) {
                title.setText("DU FÖRLORADE!");
                topHalf.remove(throwDice);
                topHalf.remove(koNumber);
                topHalf.remove(pointsLabel);
                for (JLabel labels : listOfDiceLabels) {
                    bottomHalf.remove(labels);
                }
                bottomHalf.add(playAgain);
                bottomHalf.add(clearScoreboard);
                try {
                    inputOutputHandler.writeResultsToFile(points + " poäng, " + name);
                    setHighscores(scoreBoardList(inputOutputHandler.resultListFromFile()));
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
        currentTotal = 0;
    }
}

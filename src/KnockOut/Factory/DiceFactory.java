package KnockOut.Factory;

public class DiceFactory {

    public DiceFactory() {
    }

    public Dice getDie(DiceEnum diceEnum){
        return switch (diceEnum) {
            case YELLOW_DICE -> new YellowDice();
            case PINK_DICE -> new PinkDice();
        };
    }
}

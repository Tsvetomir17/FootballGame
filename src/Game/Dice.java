package Game;

import java.util.Random;

public class Dice {
    private final int number;
    private final Random random;

    private static final Dice instance = new Dice();

    private Dice(){

        this.number = 6;
        this.random = new Random();
    }

    public static Dice getInstance(){

        return instance;
    }
    public int rollDice(){

        return random.nextInt(number) + 1;
    }
}

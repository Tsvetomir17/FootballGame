package Game;

import java.util.Random;

public class Dice {
    private final int number;
    private Random random;

    public Dice(int number){
        this.number = number;
        this.random = new Random();
    }

    public int rollDice(){
        return random.nextInt(number) + 1;
    }
}

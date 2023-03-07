package GameModes;

import Game.Dice;
import Player.Player;


public class SimVariations {
    private final static int FIRST_LEVEL_OVERALL_RATING = 40;
    private final static int SECOND_LEVEL_OVERALL_RATING = 60;
    private final static int THIRD_LEVEL_OVERALL_RATING = 80;
    private static final Dice dice = new Dice(6);
    private static int randomRollDiceOne;
    private static int randomRollDiceTwo;

    public static void simulateMatch(Player player){
        int playerCurrentOverall = player.getCurrentPlayerOverall();

        if(playerCurrentOverall < FIRST_LEVEL_OVERALL_RATING)
            simulateMatch(player,8,10);
        else if(playerCurrentOverall < SECOND_LEVEL_OVERALL_RATING)
            simulateMatch(player,7,9);
        else if(playerCurrentOverall< THIRD_LEVEL_OVERALL_RATING)
            simulateMatch(player,6,8);
        else
            simulateMatch(player,5,7);
    }

    public static void simulateMatch(Player player, int minimumPointsNotToLose, int pointsNeededToWin){
        randomRollDiceOne = dice.rollDice();
        randomRollDiceTwo = dice.rollDice();

        System.out.println("You rolled " + randomRollDiceOne + " and " + randomRollDiceTwo);

        if(randomRollDiceOne + randomRollDiceTwo < minimumPointsNotToLose){
            System.out.println("You lost the match");
        }
        else if(randomRollDiceTwo + randomRollDiceOne >= pointsNeededToWin){
            System.out.println("You won the match");
            //give 6 points
        }
        else{
            System.out.println("You made a draw");
            //give 2 points
        }
    }

}

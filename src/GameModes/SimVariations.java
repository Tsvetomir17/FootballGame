package GameModes;

import Game.Dice;
import Player.Player;

import static GameModes.FinalVariables.*;


public class SimVariations {

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
        int randomRollDiceOne = Dice.getInstance().rollDice();
        int randomRollDiceTwo = Dice.getInstance().rollDice();

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

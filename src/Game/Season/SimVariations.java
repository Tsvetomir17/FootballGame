package Game.Season;

import Game.Dice;
import Player.Player;

import static Game.FinalVariables.*;
import static Game.Season.PrintFunctionsForSeason.printForOneSim;


public class SimVariations {

    public static void simulateMatch(Player player) throws InterruptedException {
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

    public static void simulateMatch(Player player, int minimumPointsNotToLose, int pointsNeededToWin) throws InterruptedException {
        int randomRollDiceOne = Dice.getInstance().rollDice();
        int randomRollDiceTwo = Dice.getInstance().rollDice();

        printForOneSim(player,randomRollDiceOne,randomRollDiceTwo);

        if(randomRollDiceOne + randomRollDiceTwo < minimumPointsNotToLose){
            System.out.println("You lost the match\n");
        }
        else if(randomRollDiceTwo + randomRollDiceOne >= pointsNeededToWin){
            System.out.println("You won the match\n");
            player.addPointsForTheCurrentSeason(6);
        }
        else{
            System.out.println("You made a draw\n");
            player.addPointsForTheCurrentSeason(2);
        }
    }

}

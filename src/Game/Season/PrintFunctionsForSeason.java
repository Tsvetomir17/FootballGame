package Game.Season;

import Player.Player;

import java.util.List;
import java.util.Map;

public class PrintFunctionsForSeason {

    public static void printStartOfTheSeasonMessage() throws InterruptedException {

        System.out.println("Here comes the season");
        System.out.println("There are five matches");
        System.out.println("The matches are played in three thirds and start with midfield versus midfield.");
        System.out.println("The first player then goes on the offensive with their attack versus the opponent’s defence");
        System.out.println();
        System.out.println("""
                Win: 6 points
                Draw: 2 points
                Lose: 0 points

                """);
        printStatusLevels();
        Thread.sleep(7000);
    }

    public static void printForEveryOneIsSIM(){

        System.out.println("Everyone is simulating their match");
        System.out.println();
    }

    public static void printForOneSim(Player player, int diceNumberOne, int diceNumberTwo) throws InterruptedException {

        System.out.println("SIM for " + player.getPlayerColour() + " team");
        Thread.sleep(2000);
        System.out.println("You rolled " + diceNumberOne + " and " + diceNumberTwo);
        Thread.sleep(1000);
    }

    public static void printStatusLevels(){

        System.out.println("Newly promoted      0-39 overall");
        System.out.println("SIM roll    2-7 Lose    8-9 Draw    10-12 Win\n");

        System.out.println("Established         40-59 overall");
        System.out.println("SIM roll    2-6 Lose    7-8 Draw    9-12 Win\n");

        System.out.println("Mid-table           60-79 overall");
        System.out.println("SIM roll    2-5 Lose    6-7 Draw    8-12 Win\n");

        System.out.println("Title contender     80+ overall");
        System.out.println("SIM roll    2-4 Lose    5-6 Draw    7-12 Win\n");
    }

    public static void printPlayersStartingPositionsAndCurrentPoints(Map<String,Player> players, List<String> teamColoursInOrder) throws InterruptedException {

        System.out.println("\n\n");
        for (int i = 0; i < players.size(); i++) {

            printStartingPositionAndCurrentPoints(players.get(teamColoursInOrder.get(i)));
        }
        System.out.println("\n\n");
        Thread.sleep(2000);
    }

    private static void printStartingPositionAndCurrentPoints(Player player){

        System.out.println(player.getPlayerColour() + "  SP: " + player.getCurrentPlayerOverall() + "  CP: " + player.getCurrentPointsInTheSeason());
    }

    public static void printForLineInPvP(String playerColour, int diceOne, int diceTwo, String rowName, double lineStats) throws InterruptedException {

        System.out.println(playerColour + " is rolling for " + rowName);
        System.out.println("You have " + lineStats + " in this line");
        Thread.sleep(2000);
        System.out.println("You rolled " + diceOne + " and " + diceTwo + '\n');
        Thread.sleep(1000);
    }
}

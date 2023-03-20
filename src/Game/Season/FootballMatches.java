package Game.Season;

import Game.Dice;
import FootballCupTeam.FootballCupTeam;
import Player.Player;

import java.util.Map;

import static Game.Season.PrintFunctionsForSeason.printForEveryOneIsSIM;
import static Game.Season.PrintFunctionsForSeason.printForLineInPvP;

public class FootballMatches {

    public static void everyPlayerIsSIM(Map<String, Player> players) throws InterruptedException {

        printForEveryOneIsSIM();

        for(Player player : players.values()){

            SimVariations.simulateMatch(player);
        }
    }

    public static void onlyOnePlayerSIM(Player player) throws InterruptedException {

        SimVariations.simulateMatch(player);
    }

    public static void matchPlayerVsPlayer(Player player1, Player player2) throws InterruptedException {

        System.out.println(player1.getPlayerColour() + "  vs  " + player2.getPlayerColour() + '\n');
        double playerOneScore = 0;
        playerOneScore += calculateOneLineInPvP(player1.getPlayerColour().toString(), player1.getFullTeam().getMidfieldOverall(), "Midfield", player2.getPlayerColour().toString(), player2.getFullTeam().getMidfieldOverall(), "Midfield");
        playerOneScore += calculateOneLineInPvP(player1.getPlayerColour().toString(), player1.getFullTeam().getAttackOverall(), "Attack", player2.getPlayerColour().toString(), player2.getFullTeam().getDefenceOverall(), "Defence");
        playerOneScore += calculateOneLineInPvP(player1.getPlayerColour().toString(), player1.getFullTeam().getDefenceOverall(), "Defence", player2.getPlayerColour().toString(), player2.getFullTeam().getAttackOverall(), "Attack");

        if(playerOneScore > 1.5){

            System.out.println(player1.getPlayerColour() + " wins the match against " + player2.getPlayerColour() + '\n');
            player1.addPointsForTheCurrentSeason(6);
        }
        else if(playerOneScore < 1.5){

            System.out.println(player2.getPlayerColour() + " wins the match against " + player1.getPlayerColour() + '\n');
            player2.addPointsForTheCurrentSeason(6);
        }
        else {

            System.out.println("The match between " + player1.getPlayerColour() + " and " + player2.getPlayerColour() + " ends in a draw\n");
            player1.addPointsForTheCurrentSeason(2);
            player2.addPointsForTheCurrentSeason(2);
        }
    }

    public static boolean matchPlayerVsFootballCupTeam(Player player, FootballCupTeam footballCupTeam) throws InterruptedException {

        System.out.println(player.getPlayerColour() + "  vs  " + footballCupTeam.getName() + '\n');
        double playerOneScore = 0;
        playerOneScore += calculateOneLineInPvP(player.getPlayerColour().toString(), player.getFullTeam().getMidfieldOverall(), "Midfield", footballCupTeam.getName(), footballCupTeam.getMidfield(), "Midfield");
        playerOneScore += calculateOneLineInPvP(player.getPlayerColour().toString(), player.getFullTeam().getAttackOverall(), "Attack", footballCupTeam.getName(), footballCupTeam.getDefence(), "Defence");
        playerOneScore += calculateOneLineInPvP(player.getPlayerColour().toString(), player.getFullTeam().getDefenceOverall(), "Defence", footballCupTeam.getName(), footballCupTeam.getAttack(), "Attack");

        if(playerOneScore > 1.5){

            System.out.println(player.getPlayerColour() + " wins the match against " + footballCupTeam.getName() + '\n');
            return true;
        }

        System.out.println(player.getPlayerColour() + " did not win the match against " + footballCupTeam.getName() + '\n');
        return false;
    }

    private static double calculateOneLineInPvP(String playerOneColour, double player1LineStats, String playerOneRowName, String playerTwoColour, double player2LineStats, String playerTwoRowName) throws InterruptedException {

        int rolledDiceOne = Dice.getInstance().rollDice();
        int rolledDiceTwo = Dice.getInstance().rollDice();
        printForLineInPvP(playerOneColour,rolledDiceOne,rolledDiceTwo,playerOneRowName,player1LineStats);
        player1LineStats += rolledDiceOne + rolledDiceTwo;

        rolledDiceOne = Dice.getInstance().rollDice();
        rolledDiceTwo = Dice.getInstance().rollDice();
        printForLineInPvP(playerTwoColour,rolledDiceOne,rolledDiceTwo,playerTwoRowName,player2LineStats);
        player2LineStats += rolledDiceOne + rolledDiceTwo;

        if(player1LineStats > player2LineStats){

            System.out.println(playerOneColour + " wins in " + playerOneRowName +'\n');
            return 1;
        }
        else if(player2LineStats > player1LineStats){

            System.out.println(playerTwoColour + " wins in " +  playerTwoRowName +'\n');
            return 0;
        }
        else{

            System.out.println("Draw in " + playerOneRowName + " -> " + playerTwoRowName+'\n');
            return 0.5;
        }
    }
}

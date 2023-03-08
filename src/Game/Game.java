package Game;

import FootballPlayer.FootballPlayer;
import Player.Player;

import java.sql.*;
import java.util.*;

import static Game.Draft.setTheFootballPlayersDraft;
import static Game.GetAllPlayersFromDB.fillTheListOfFootballPlayers;
import static Game.Scouting.scouting;
import static Game.SetPlayersColoursAndMoney.*;
import static Game.PlayerDevelopment.upgradeFootballPlayers;
import static Game.Upgrades.upgrades;

public class Game {
    private static int playersInTheGameSize;
    static Stack<FootballPlayer> theFullDeckOfFootballPlayers;
    static Map<String, Player> players;
    static List<String> teamColoursInCurrentOrder;
    private static final Game instance = new Game();

    private Game(){
        players = new HashMap<String, Player>();
        teamColoursInCurrentOrder = new ArrayList<>();
        theFullDeckOfFootballPlayers = new Stack<>();
    }

    public static Game getGameInstance(){
        return instance;
    }

    private void setPlayersInTheGameSize(){
        playersInTheGameSize = choiceMadeByTheUserValidation(2,6);
    }

    public void startGame() throws SQLException, ClassNotFoundException {
        printStartingMessage();
        setPlayersInTheGameSize();
        playersChoosingTeamColours();
        setEveryPlayerStartingMoney();
        fillTheListOfFootballPlayers();
        setTheFootballPlayersDraft();
        upgradeFootballPlayers();
        scouting();
        upgrades();
    }

    public static void printFootballPlayers(List<FootballPlayer> players){
        for (int i = 0; i < players.size(); i++) {
            System.out.print((i+1) + ". ");
            players.get(i).printFootballPlayer();
        }
    }

    public static void printFootballPlayersAsCards(List<FootballPlayer> players){
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i+1) + ".");
            players.get(i).printFootballPlayerAsCard();
        }
    }

    static int choiceMadeByTheUserValidation(int minimumValue, int maximumValue){
        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        while (choice < minimumValue || choice > maximumValue) {
            System.out.println("Bad input! Try again: ");
            choice = scanner.nextInt();
        }

        return choice;
    }

    public static int getPlayersInTheGameSize(){
        return playersInTheGameSize;
    }
}

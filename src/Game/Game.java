package Game;

import FootballPlayer.FootballPlayer;
import Player.Player;

import java.sql.*;
import java.util.*;

import static Game.AfterSeason.afterSeason;
import static Game.DeadlineDay.deadlineDay;
import static Game.Discarding.discarding;
import static Game.Draft.setTheFootballPlayersDraft;
import static Game.GetAllPlayersFromDB.fillTheListOfFootballPlayers;
import static Game.LineUp.setLineUp;
import static Game.Scouting.scouting;
import static Game.SetPlayersColoursAndMoney.*;
import static Game.PlayerDevelopment.upgradeFootballPlayers;
import static Game.Upgrades.upgrades;
import static Season.Season.season;

public class Game {
    private static int playersInTheGameSize;
    static Stack<FootballPlayer> theFullDeckOfFootballPlayers;
    static Map<String, Player> players;
    static List<String> teamColoursInCurrentOrder;
    private static final Game instance = new Game();

    private Game(){
        players = new HashMap<>();
        teamColoursInCurrentOrder = new ArrayList<>();
        theFullDeckOfFootballPlayers = new Stack<>();
    }

    public static Game getGameInstance(){
        return instance;
    }

    private static void setPlayersInTheGameSize(){
        playersInTheGameSize = choiceMadeByTheUserValidation(2,6);
    }

    public void startGame() throws SQLException, ClassNotFoundException, InterruptedException {
        firstSeason();
        nextSeason();
    }

    private static void firstSeason() throws SQLException, ClassNotFoundException, InterruptedException {
        printStartingMessage();
        setPlayersInTheGameSize();
        playersChoosingTeamColours();
        setEveryPlayerStartingMoney();
        fillTheListOfFootballPlayers();
        setTheFootballPlayersDraft();
        upgradeFootballPlayers();
        scouting();
        upgrades();
        deadlineDay();
        setLineUp();
        season(players,teamColoursInCurrentOrder);
    }
    private static void nextSeason() throws SQLException, ClassNotFoundException, InterruptedException {
        while(afterSeason()){
            upgradeFootballPlayers();
            discarding();
            scouting();
            upgrades();
            deadlineDay();
            setLineUp();
            season(players,teamColoursInCurrentOrder);
        }
    }

    public static void printFootballPlayers(List<FootballPlayer> players){
        for (int i = 0; i < players.size(); i++) {
            System.out.print((i+1) + ". ");
            players.get(i).printFootballPlayer();
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

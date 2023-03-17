package Game;

import FootballPlayer.FootballPlayer;
import Player.Player;

import java.sql.*;
import java.util.*;

import static Game.AfterSeason.AfterSeason.afterSeason;
import static Game.Preseason.DeadlineDay.deadlineDay;
import static Game.Preseason.Discarding.discarding;
import static Game.PrintFunctionsForDifferentStagesOfTheGame.printStageName;
import static Game.PrintFunctionsForDifferentStagesOfTheGame.printStartingMessage;
import static Game.Starting.Draft.setTheFootballPlayersDraft;
import static Game.Starting.GetAllPlayersFromDB.fillTheListOfFootballPlayers;
import static Game.Preseason.LineUp.setLineUp;
import static Game.Preseason.Scouting.scouting;
import static Game.Starting.SetPlayersColoursAndMoney.*;
import static Game.Preseason.PlayerDevelopment.upgradeFootballPlayers;
import static Game.Preseason.Upgrades.upgrades;
import static Game.Season.Season.season;

public class Game {
    private static int playersInTheGameSize;
    private static Stack<FootballPlayer> theFullDeckOfFootballPlayers;
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
    public static int getPlayersInTheGameSize(){
        return playersInTheGameSize;
    }
    public static FootballPlayer getTheTopPlayerFromTheDeck(){
        return theFullDeckOfFootballPlayers.pop();
    }

    public static void pushPlayerToTheDeck(FootballPlayer footballPlayer){
        theFullDeckOfFootballPlayers.push(footballPlayer);
    }
    public static Map<String, Player> getPlayers() {
        return players;
    }
    public static List<String> getTeamColoursInCurrentOrder() {
        return teamColoursInCurrentOrder;
    }

    public void startGame() throws SQLException, ClassNotFoundException, InterruptedException {
        firstSeason();
        nextSeason();
    }

    private void firstSeason() throws SQLException, ClassNotFoundException, InterruptedException {
        printStartingMessage();
        setPlayersInTheGameSize();
        setPlayersColoursAndMoney();
        fillTheListOfFootballPlayers();

        printStageName("DRAFT");
        setTheFootballPlayersDraft();
        printStageName("PLAYER DEVELOPMENT");
        upgradeFootballPlayers();
        scoutingUpgradesDeadlineLineUpAndSeason();
    }
    private void nextSeason() throws SQLException, ClassNotFoundException, InterruptedException {
        while(afterSeason()){
            printStageName("PLAYER DEVELOPMENT");
            upgradeFootballPlayers();
            printStageName("DISCARDING");
            discarding();
            scoutingUpgradesDeadlineLineUpAndSeason();
        }
    }

    private void scoutingUpgradesDeadlineLineUpAndSeason() throws InterruptedException {
        printStageName("SCOUTING");
        scouting();
        printStageName("UPGRADES");
        upgrades();
        printStageName("DEADLINE DAY");
        deadlineDay();
        printStageName("LINE UPS");
        setLineUp();
        printStageName("SEASON");
        season();
    }

    public static int choiceMadeByTheUserValidation(int minimumValue, int maximumValue){
        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        while (choice < minimumValue || choice > maximumValue) {
            System.out.println("Bad input! Try again: ");
            choice = scanner.nextInt();
        }

        return choice;
    }

}

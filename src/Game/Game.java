package Game;

import FootballPlayer.FootballPlayer;
import Game.AfterSeason.AfterSeason;
import Game.Starting.Draft;
import Game.Starting.GetAllPlayersFromDB;
import Game.Starting.SetPlayersColoursAndMoney;
import Player.Player;
import Game.Preseason.DeadlineDay;
import Game.Preseason.Discarding;
import Game.Preseason.LineUp;
import Game.Preseason.PlayerDevelopment;
import Game.Preseason.Scouting;
import Game.Preseason.Upgrades;
import Game.Season.Season;
import java.sql.*;
import java.util.*;


public class Game {

    private int playersInTheGameSize;
    private final Stack<FootballPlayer> theFullDeckOfFootballPlayers;
    private final Map<String, Player> players;
    private final List<String> teamColoursInCurrentOrder;
    private final PrintFunctionsForDifferentStagesOfTheGame printFunctionsForDifferentStagesOfTheGame = new PrintFunctionsForDifferentStagesOfTheGame();
    private final DeadlineDay deadlineDay = new DeadlineDay();
    private final Discarding discarding = new Discarding();
    private final LineUp lineUp = new LineUp();
    private final PlayerDevelopment playerDevelopment = new PlayerDevelopment();
    private final Scouting scouting = new Scouting();
    private final Upgrades upgrades = new Upgrades();
    private final Season season = new Season();

    private Game(){

        players = new HashMap<>();
        teamColoursInCurrentOrder = new ArrayList<>();
        theFullDeckOfFootballPlayers = new Stack<>();
    }

    public static Game getGameInstance(){

        return instance;
    }
    public int getPlayersInTheGameSize(){

        return playersInTheGameSize;
    }
    public FootballPlayer getTheTopPlayerFromTheDeck(){

        return theFullDeckOfFootballPlayers.pop();
    }
    public void pushPlayerToTheDeck(FootballPlayer footballPlayer){

        theFullDeckOfFootballPlayers.push(footballPlayer);
    }
    public Map<String, Player> getPlayers() {

        return players;
    }
    public List<String> getTeamColoursInCurrentOrder() {

        return teamColoursInCurrentOrder;
    }

    public void startGame() throws SQLException, ClassNotFoundException, InterruptedException {

        firstSeason();
        nextSeason();
    }

    private static final Game instance = new Game();

    private void setPlayersInTheGameSize(){

        playersInTheGameSize = InputValidator.choiceMadeByTheUserValidation(2,6);
    }

    private void firstSeason() throws SQLException, ClassNotFoundException, InterruptedException {

        SetPlayersColoursAndMoney setPlayersColoursAndMoney = new SetPlayersColoursAndMoney();
        GetAllPlayersFromDB getAllPlayersFromDB = new GetAllPlayersFromDB();
        Draft draft = new Draft();

        printFunctionsForDifferentStagesOfTheGame.printStartingMessage();
        setPlayersInTheGameSize();
        setPlayersColoursAndMoney.action(getGameInstance());
        getAllPlayersFromDB.action(getGameInstance());

        printFunctionsForDifferentStagesOfTheGame.printStageName("DRAFT");
        draft.action(getGameInstance());
        printFunctionsForDifferentStagesOfTheGame.printStageName("PLAYER DEVELOPMENT");
        playerDevelopment.action(getGameInstance());
        scoutingUpgradesDeadlineLineUpAndSeason();
    }

    private void nextSeason() throws SQLException, ClassNotFoundException, InterruptedException {

        AfterSeason afterSeason = new AfterSeason();
        while(afterSeason.action(getGameInstance())){

            printFunctionsForDifferentStagesOfTheGame.printStageName("PLAYER DEVELOPMENT");
            playerDevelopment.action(getGameInstance());
            printFunctionsForDifferentStagesOfTheGame.printStageName("DISCARDING");
            discarding.action(getGameInstance());
            scoutingUpgradesDeadlineLineUpAndSeason();
        }
    }

    private void scoutingUpgradesDeadlineLineUpAndSeason() throws InterruptedException, SQLException, ClassNotFoundException {

        printFunctionsForDifferentStagesOfTheGame.printStageName("SCOUTING");
        scouting.action(getGameInstance());
        printFunctionsForDifferentStagesOfTheGame.printStageName("UPGRADES");
        upgrades.action(getGameInstance());
        printFunctionsForDifferentStagesOfTheGame.printStageName("DEADLINE DAY");
        deadlineDay.action(getGameInstance());
        printFunctionsForDifferentStagesOfTheGame.printStageName("LINE UPS");
        lineUp.action(getGameInstance());
        printFunctionsForDifferentStagesOfTheGame.printStageName("SEASON");
        season.season(getGameInstance());
    }
}

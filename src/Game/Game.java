package Game;

import FootballPlayer.FootballPlayer;
import Player.Player;

import java.sql.*;
import java.util.*;

public class Game {
    private int playersInTheGameSize;
    private Stack<FootballPlayer> theFullDeckOfFootballPlayers;
    private Map<String, Player> players;
    private List<String> teamColoursInCurrentOrder;
    private static Game instance = new Game();

    private Game(){
        players = new HashMap<String, Player>();
        teamColoursInCurrentOrder = new ArrayList<>();
        theFullDeckOfFootballPlayers = new Stack<>();
    }

    public static Game getGameInstance(){
        return instance;
    }

    private void printStartingMessage(){
        System.out.println("Hello! Welcome to the FootballCup game!");
        System.out.println("Minimum players size: 2");
        System.out.println("Maximum players size: 6");
        System.out.println("Please type how many players are going to play: ");
    }

    private void setPlayersInTheGameSize(){
        playersInTheGameSize = choiceMadeByTheUserValidation(2,6);
    }

    private String playersChoosingTeamColoursHelper(List<String> listOfRemainingColours, int indexOfCurrentPlayer){

        for (int colourIterator = 0; colourIterator < listOfRemainingColours.size(); colourIterator++) {
            System.out.println(colourIterator+1 + ". " + listOfRemainingColours.get(colourIterator));
        }
        System.out.println("Player" + (indexOfCurrentPlayer + 1) + ", please select the colour of your team: ");
        int choiceByTheUser = choiceMadeByTheUserValidation(0, listOfRemainingColours.size()) - 1;

        return listOfRemainingColours.get(choiceByTheUser);
    }

    private void playersChoosingTeamColours(){
        List<String> remainingColours = new ArrayList<>();
        remainingColours.add("Red");
        remainingColours.add("Blue");
        remainingColours.add("Green");
        remainingColours.add("Yellow");
        remainingColours.add("Purple");
        remainingColours.add("Pink");

        for (int indexOfCurrentPlayer = 0; indexOfCurrentPlayer < playersInTheGameSize; indexOfCurrentPlayer++) {
            String result = playersChoosingTeamColoursHelper(remainingColours,indexOfCurrentPlayer);
            players.put(result,new Player(result));
            teamColoursInCurrentOrder.add(result);
            remainingColours.remove(result);
        }
    }

    private int makingChoiceForStartingMoney(){
        System.out.println("Choose starting money for the players: ");
        System.out.println("1. 150M.");
        System.out.println("2. 120M.");
        System.out.println("3.  90M.");

        int choice = choiceMadeByTheUserValidation(1,3);

        if(choice == 1) return 150;
        else if(choice == 2) return 120;
        return 90;
    }

    private void setEveryPlayerStartingMoney(){
        int moneyForEveryPlayer = makingChoiceForStartingMoney();
        for(Player player : players.values()){
            player.setPlayerMoney(moneyForEveryPlayer);
        }
    }

    public void startGame() throws SQLException, ClassNotFoundException {
        printStartingMessage();
        setPlayersInTheGameSize();
        playersChoosingTeamColours();
        setEveryPlayerStartingMoney();
        fillTheListOfFootballPlayers();
        setTheFootballPlayersDraft();

    }

    public void printPlayers(List<FootballPlayer> players){
        for (int i = 0; i < players.size(); i++) {
            System.out.print((i+1) + ". ");
            players.get(i).printFootballPlayer();
        }
    }

    private void fillTheListOfFootballPlayers() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/FootballGamePlayers";
        String username = "postgres";
        String password = "password";

        Connection connection = DriverManager.getConnection(url,username,password);
        String query = "SELECT * FROM \"FootballPlayers\" ORDER BY RANDOM()";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){

            FootballPlayer footballPlayerToPushInTheDeck = new FootballPlayer(
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getBoolean(6),
                    resultSet.getBoolean(7),
                    resultSet.getBoolean(8),
                    resultSet.getInt(9),
                    resultSet.getInt(10)
            );
            theFullDeckOfFootballPlayers.push(footballPlayerToPushInTheDeck);
        }
    }

    private void setTheFootballPlayersDraft(){
        List<FootballPlayer> current16PlayersOnTheDraft = new ArrayList<>();
        int currentIndexForTheTeamToPick = 0;
        Player currentPlayerToPick;
        for (int i = 0; i < 1; i++) {

            for (int j = 0; j < 16; j++) {
                current16PlayersOnTheDraft.add(theFullDeckOfFootballPlayers.pop());
            }

            while (!current16PlayersOnTheDraft.isEmpty()) {
                printPlayers(current16PlayersOnTheDraft);
                currentPlayerToPick = players.get(teamColoursInCurrentOrder.get(currentIndexForTheTeamToPick));
                System.out.println(currentPlayerToPick.getPlayerColour() + ", it is your turn to pick");
                int choiceByPlayer = choiceMadeByTheUserValidation(0, current16PlayersOnTheDraft.size()) -1;

                currentPlayerToPick.addFootballPlayerToTheTeam(current16PlayersOnTheDraft.get(choiceByPlayer));

                current16PlayersOnTheDraft.remove(choiceByPlayer);
                currentIndexForTheTeamToPick = setCurrentIndexForTeamToPick(currentIndexForTheTeamToPick);
            }
        }
    }

    private int choiceMadeByTheUserValidation(int minimumValue, int maximumValue){
        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        while (choice < minimumValue || choice > maximumValue) {
            System.out.println("Bad input! Try again: ");
            choice = scanner.nextInt();
        }

        return choice;
    }

    private int setCurrentIndexForTeamToPick(int index){
        return ++index == playersInTheGameSize ? 0 : index;
    }
}

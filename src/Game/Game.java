package Game;

import FootballPlayer.FootballPlayer;
import Player.Player;

import java.sql.SQLOutput;
import java.util.*;

public class Game {
    private int playersInTheGameSize;
    private List<FootballPlayer> theFullListOfFootballPlayers;
//    private List<Keystaff> theFullListOfKeyStaffs;
    private Map<String, Player> players;
    private List<String> teamColoursInCurrentOrder;
    private static Game instance = new Game();

    private Game(){
        players = new HashMap<String, Player>();
        teamColoursInCurrentOrder = new ArrayList<>();
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
        Scanner scanner = new Scanner(System.in);
        int choiceMadeByTheUser = scanner.nextInt();

        while(choiceMadeByTheUser < 2 || choiceMadeByTheUser > 6){
            System.out.println("Bad input! Please try again:");
            choiceMadeByTheUser = scanner.nextInt();
        }

        playersInTheGameSize = choiceMadeByTheUser;
    }

    private String playersChoosingTeamColoursHelper(List<String> listOfRemainingColours, int indexOfCurrentPlayer){

        for (int colourIterator = 0; colourIterator < listOfRemainingColours.size(); colourIterator++) {
            System.out.println(colourIterator+1 + ". " + listOfRemainingColours.get(colourIterator));
        }
        int choice;
        System.out.println("Player" + (indexOfCurrentPlayer + 1) + ", please select the colour of your team: ");
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        while (choice <= 0 || choice > listOfRemainingColours.size()){
            System.out.println("Bad input! Please choose a valid number: ");
            choice = scanner.nextInt();
        }
        return listOfRemainingColours.get(choice - 1);
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

        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        while(choice < 1 || choice > 3){
            System.out.println("Bad input, please try again: ");
            choice = scanner.nextInt();
        }

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

    public void startGame(){
        printStartingMessage();
        setPlayersInTheGameSize();
        playersChoosingTeamColours();
        setEveryPlayerStartingMoney();

    }

    public void printPlayers(){

    }


//    private void setTheFootballPlayersDraft(){
//
//    }
}

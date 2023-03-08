package Game;

import Player.Player;

import java.util.ArrayList;
import java.util.List;

import static Game.Game.*;

public class SetPlayersColoursAndMoney {

    public static void printStartingMessage(){
        System.out.println("Hello! Welcome to the FootballCup game!");
        System.out.println("Minimum players size: 2");
        System.out.println("Maximum players size: 6");
        System.out.println("Please type how many players are going to play: ");
    }

    public static String playersChoosingTeamColoursHelper(List<String> listOfRemainingColours, int indexOfCurrentPlayer){

        for (int colourIterator = 0; colourIterator < listOfRemainingColours.size(); colourIterator++) {
            System.out.println(colourIterator+1 + ". " + listOfRemainingColours.get(colourIterator));
        }
        System.out.println("Player" + (indexOfCurrentPlayer + 1) + ", please select the colour of your team: ");
        int choiceByTheUser = choiceMadeByTheUserValidation(0, listOfRemainingColours.size()) - 1;

        return listOfRemainingColours.get(choiceByTheUser);
    }

    public static void playersChoosingTeamColours(){
        List<String> remainingColours = new ArrayList<>();
        remainingColours.add("Red");
        remainingColours.add("Blue");
        remainingColours.add("Green");
        remainingColours.add("Yellow");
        remainingColours.add("Purple");
        remainingColours.add("Pink");

        for (int indexOfCurrentPlayer = 0; indexOfCurrentPlayer < getPlayersInTheGameSize(); indexOfCurrentPlayer++) {
            String result = playersChoosingTeamColoursHelper(remainingColours,indexOfCurrentPlayer);
            players.put(result,new Player(result));
            teamColoursInCurrentOrder.add(result);
            remainingColours.remove(result);
        }
    }

    public static int makingChoiceForStartingMoney(){
        System.out.println("Choose starting money for the players: ");
        System.out.println("1. 150M.");
        System.out.println("2. 120M.");
        System.out.println("3.  90M.");

        int choice = choiceMadeByTheUserValidation(1,3);

        if(choice == 1) return 150;
        else if(choice == 2) return 120;
        return 90;
    }

    public static void setEveryPlayerStartingMoney(){
        int moneyForEveryPlayer = makingChoiceForStartingMoney();
        for(Player player : players.values()){
            player.addMoneyToThePlayer(moneyForEveryPlayer);
        }
    }
}

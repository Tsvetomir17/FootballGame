package Game.Starting;

import Player.Player;

import java.util.ArrayList;
import java.util.List;

import static Game.Game.*;

public class SetPlayersColoursAndMoney {

    public static void setPlayersColoursAndMoney(){
        playersChoosingTeamColours();
        setEveryPlayerStartingMoney();
    }

    private static String playersChoosingTeamColoursHelper(List<String> listOfRemainingColours, int indexOfCurrentPlayer){

        for (int colourIterator = 0; colourIterator < listOfRemainingColours.size(); colourIterator++) {
            System.out.println((colourIterator+1) + ". " + listOfRemainingColours.get(colourIterator));
        }
        System.out.println("Player №" + (indexOfCurrentPlayer + 1) + ", please select the colour of your team: ");

        return listOfRemainingColours.get(choiceMadeByTheUserValidation(0, listOfRemainingColours.size()) - 1);
    }

    private static void playersChoosingTeamColours(){
        List<String> remainingColours = getColoursList();

        for (int indexOfCurrentPlayer = 0; indexOfCurrentPlayer < getPlayersInTheGameSize(); indexOfCurrentPlayer++) {

            String result = playersChoosingTeamColoursHelper(remainingColours,indexOfCurrentPlayer);
            getPlayers().put(result,new Player(result));
            getTeamColoursInCurrentOrder().add(result);
            remainingColours.remove(result);
        }
    }

    private static List<String> getColoursList(){
        List<String> colours = new ArrayList<>();
        colours.add("Red");
        colours.add("Blue");
        colours.add("Green");
        colours.add("Yellow");
        colours.add("Purple");
        colours.add("Pink");

        return colours;
    }

    private static void printMoneyChoiceMessage(){
        System.out.println("Choose starting money for the players: ");
        System.out.println("1. 150M.");
        System.out.println("2. 120M.");
        System.out.println("3.  90M.");
    }
    private static int makingChoiceForStartingMoney(){
        printMoneyChoiceMessage();

        int choice = choiceMadeByTheUserValidation(1,3);

        if(choice == 1) return 150;
        else if(choice == 2) return 120;
        return 90;
    }

    private static void setEveryPlayerStartingMoney(){
        int moneyForEveryPlayer = makingChoiceForStartingMoney();
        for(Player player : getPlayers().values()){
            player.addMoneyToThePlayer(moneyForEveryPlayer);
        }
    }
}

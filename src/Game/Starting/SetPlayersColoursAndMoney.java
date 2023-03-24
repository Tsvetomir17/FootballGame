package Game.Starting;

import Game.Game;
import Player.Player;
import java.util.ArrayList;
import java.util.List;
import Game.InputValidator;

public class SetPlayersColoursAndMoney implements StartingAction {

    @Override
    public void action(Game game) {

        playersChoosingTeamColours(game);
        setEveryPlayerStartingMoney(game);
    }

    private String playersChoosingTeamColoursHelper(List<String> listOfRemainingColours, int indexOfCurrentPlayer){

        for (int colourIterator = 0; colourIterator < listOfRemainingColours.size(); colourIterator++) {

            System.out.println((colourIterator+1) + ". " + listOfRemainingColours.get(colourIterator));
        }
        System.out.println("Player №" + (indexOfCurrentPlayer + 1) + ", please select the colour of your team: ");

        return listOfRemainingColours.get(InputValidator.choiceMadeByTheUserValidation(1, listOfRemainingColours.size()) - 1);
    }

    private void playersChoosingTeamColours(Game game){

        List<String> remainingColours = getColoursList();

        for (int indexOfCurrentPlayer = 0; indexOfCurrentPlayer < game.getPlayersInTheGameSize(); indexOfCurrentPlayer++) {

            String result = playersChoosingTeamColoursHelper(remainingColours,indexOfCurrentPlayer);
            game.getPlayers().put(result,new Player(result));
            game.getTeamColoursInCurrentOrder().add(result);
            remainingColours.remove(result);
        }
    }

    private List<String> getColoursList(){

        List<String> colours = new ArrayList<>();
        colours.add("Red");
        colours.add("Blue");
        colours.add("Green");
        colours.add("Yellow");
        colours.add("Purple");
        colours.add("Pink");

        return colours;
    }

    private void printMoneyChoiceMessage(){

        System.out.println("Choose starting money for the players: ");
        System.out.println("1. 150M.");
        System.out.println("2. 120M.");
        System.out.println("3.  90M.");
    }
    private int makingChoiceForStartingMoney(){

        printMoneyChoiceMessage();

        int choice = InputValidator.choiceMadeByTheUserValidation(1,3);

        if(choice == 1) return 150;
        else if(choice == 2) return 120;
        return 90;
    }

    private void setEveryPlayerStartingMoney(Game game){

        int moneyForEveryPlayer = makingChoiceForStartingMoney();
        for(Player player : game.getPlayers().values()){

            player.addMoneyToThePlayer(moneyForEveryPlayer);
        }
    }
}

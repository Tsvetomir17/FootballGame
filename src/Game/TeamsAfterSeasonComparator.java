package Game;

import Player.Player;

import java.util.Comparator;

import static Game.Game.players;

public class TeamsAfterSeasonComparator implements Comparator<String> {

    @Override
    public int compare(String colourOne, String colourTwo) {

        Player playerOne = players.get(colourOne);
        Player playerTwo = players.get(colourTwo);

        int currentPointCompare = playerTwo.getCurrentPointsInTheSeason() - playerOne.getCurrentPointsInTheSeason();
        if(currentPointCompare != 0){
            return currentPointCompare;
        }

        return playerOne.getCurrentPlayerOverall() - playerTwo.getCurrentPlayerOverall();
    }
}

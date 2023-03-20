package Game.AfterSeason;

import Player.Player;

import java.util.Comparator;

import static Game.Game.getPlayers;

public class TeamsAfterSeasonComparator implements Comparator<String> {

    @Override
    public int compare(String colourOne, String colourTwo) {

        Player playerOne = getPlayers().get(colourOne);
        Player playerTwo = getPlayers().get(colourTwo);

        int currentPointCompare = playerTwo.getCurrentPointsInTheSeason() - playerOne.getCurrentPointsInTheSeason();
        if(currentPointCompare != 0){

            return currentPointCompare;
        }

        return playerOne.getCurrentPlayerOverall() - playerTwo.getCurrentPlayerOverall();
    }
}

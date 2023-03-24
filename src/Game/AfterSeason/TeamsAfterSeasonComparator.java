package Game.AfterSeason;

import Game.Game;
import Player.Player;
import java.util.Comparator;

public class TeamsAfterSeasonComparator implements Comparator<String> {

    @Override
    public int compare(String colourOne, String colourTwo) {

        Player playerOne = Game.getGameInstance().getPlayers().get(colourOne);
        Player playerTwo = Game.getGameInstance().getPlayers().get(colourTwo);

        int currentPointCompare = playerTwo.getCurrentPointsInTheSeason() - playerOne.getCurrentPointsInTheSeason();
        if(currentPointCompare != 0){

            return currentPointCompare;
        }

        return playerOne.getCurrentOverall() - playerTwo.getCurrentOverall();
    }
}

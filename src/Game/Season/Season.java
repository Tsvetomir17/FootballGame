package Game.Season;

import Player.Player;

import java.util.Map;

import static Game.Game.getPlayers;
import static Game.Game.getTeamColoursInCurrentOrder;
import static Game.Season.GameModes.*;
import static Game.Season.PrintFunctionsForSeason.printStartOfTheSeasonMessage;

public class Season {

    public static void season() throws InterruptedException {
        printStartOfTheSeasonMessage();
        setALlPlayersCurrentPointsAsTheirOverall(getPlayers());

        switch (getPlayers().size()){
            case 2 -> gameModeForTwoPlayers(getPlayers(),getTeamColoursInCurrentOrder());
            case 3 -> gameModeForThreePlayers(getPlayers(),getTeamColoursInCurrentOrder());
            case 4 -> gameModeForFourPlayers(getPlayers(),getTeamColoursInCurrentOrder());
            case 5 -> gameModeForFivePlayers(getPlayers(),getTeamColoursInCurrentOrder());
            case 6 -> gameModeForSixPlayers(getPlayers(),getTeamColoursInCurrentOrder());
        }
    }

    private static void setALlPlayersCurrentPointsAsTheirOverall(Map<String, Player> players){
        for (Map.Entry<String, Player> player : players.entrySet()){
            player.getValue().setCurrentPointsInTheSeason(player.getValue().getCurrentPlayerOverall());
        }
    }


}

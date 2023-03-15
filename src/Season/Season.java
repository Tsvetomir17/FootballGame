package Season;

import Player.Player;

import java.util.List;
import java.util.Map;

import static Season.GameModes.*;
import static Season.PrintFunctionsForSeason.printStartOfTheSeasonMessage;

public class Season {

    public static void season(Map<String, Player> players, List<String> teamColoursInCurrentOrder) throws InterruptedException {
        printStartOfTheSeasonMessage();
        setALlPlayersCurrentPointsAsTheirOverall(players);

        switch (players.size()){
            case 2 -> gameModeForTwoPlayers(players,teamColoursInCurrentOrder);
            case 3 -> gameModeForThreePlayers(players,teamColoursInCurrentOrder);
            case 4 -> gameModeForFourPlayers(players,teamColoursInCurrentOrder);
            case 5 -> gameModeForFivePlayers(players,teamColoursInCurrentOrder);
            case 6 -> gameModeForSixPlayers(players,teamColoursInCurrentOrder);
        }
    }

    private static void setALlPlayersCurrentPointsAsTheirOverall(Map<String, Player> players){
        for (Map.Entry<String, Player> player : players.entrySet()){
            player.getValue().setCurrentPointsInTheSeason(player.getValue().getCurrentPlayerOverall());
        }
    }


}

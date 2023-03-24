package Game.Season;

import Game.Game;
import Player.Player;
import java.util.Map;

public class Season {
    PrintFunctionsForSeason printFunctionsForSeason = new PrintFunctionsForSeason();
    GameModes gameModes = new GameModes();

    public void season(Game game) throws InterruptedException {

        printFunctionsForSeason.printStartOfTheSeasonMessage();
        setALlPlayersCurrentPointsAsTheirOverall(game.getPlayers());

        switch (game.getPlayers().size()){

            case 2 -> gameModes.gameModeForTwoPlayers(game.getPlayers(),game.getTeamColoursInCurrentOrder());
            case 3 -> gameModes.gameModeForThreePlayers(game.getPlayers(),game.getTeamColoursInCurrentOrder());
            case 4 -> gameModes.gameModeForFourPlayers(game.getPlayers(),game.getTeamColoursInCurrentOrder());
            case 5 -> gameModes.gameModeForFivePlayers(game.getPlayers(),game.getTeamColoursInCurrentOrder());
            case 6 -> gameModes.gameModeForSixPlayers(game.getPlayers(),game.getTeamColoursInCurrentOrder());
        }
    }

    private void setALlPlayersCurrentPointsAsTheirOverall(Map<String, Player> players){

        for (Map.Entry<String, Player> player : players.entrySet()){

            player.getValue().setCurrentPointsInTheSeason(player.getValue().getCurrentOverall());
        }
    }


}

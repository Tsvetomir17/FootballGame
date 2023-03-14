package Season;

import Player.Player;

import java.util.List;
import java.util.Map;

import static Season.FootballMatches.*;

public class GameModes {

    public static void gameModeForTwoPlayers(Map<String, Player> players, List<String> teamColoursInCurrentOrder){

        // Round 1
        everyPlayerIsSIM(players); // SIM

        // Round 2
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(1))); // 1 v 2

        // Round 3
        everyPlayerIsSIM(players); // SIM

        // Round 4
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(0))); // 2 v 1

        // Round 5
        everyPlayerIsSIM(players); // SIM
    }

    public static void gameModeForThreePlayers(Map<String, Player> players, List<String> teamColoursInCurrentOrder){

        // Round 1
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(1))); // 1 v 2
        onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(2)));                                                   // 3 SIM

        // Round 2
        everyPlayerIsSIM(players);                                                                                         // SIM

        // Round 3
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(2))); // 1 v 3
        onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(1)));                                                   // 2 SIM

        // Round 4
        everyPlayerIsSIM(players);                                                                                         // SIM

        // Round 5
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(2))); // 2 v 3
        onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(0)));                                                   // 1 SIM
    }

    public static void gameModeForFourPlayers(Map<String, Player> players, List<String> teamColoursInCurrentOrder){

        // Round 1
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(3))); // 1 v 4
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(2))); // 2 v 3

        // Round 2
        everyPlayerIsSIM(players);                                                                                         // SIM

        // Round 3
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(2))); // 1 v 3
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(3))); // 2 v 4

        // Round 4
        everyPlayerIsSIM(players);                                                                                         // SIM

        // Round 5
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(1))); // 1 v 2
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(3))); // 3 v 4
    }

    public static void gameModeForFivePlayers(Map<String, Player> players, List<String> teamColoursInCurrentOrder){

        // Round 1
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(1))); // 1 v 2
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(3))); // 3 v 4
        onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(4)));                                                   // 5 SIM

        // Round 2
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(2))); // 1 v 3
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(4))); // 2 v 5
        onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(3)));                                                   // 4 SIM

        // Round 3
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(4))); // 1 v 5
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(3))); // 2 v 4
        onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(2)));                                                   // 3 SIM

        // Round 4
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(3))); // 1 v 4
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(4))); // 3 v 5
        onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(1)));                                                   // 2 SIM

        // Round 5
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(2))); // 2 v 3
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(3)), players.get(teamColoursInCurrentOrder.get(4))); // 4 v 5
        onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(0)));                                                   // 1 SIM
    }

    public static void gameModeForSixPlayers(Map<String, Player> players, List<String> teamColoursInCurrentOrder){
        // Round 1
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(5))); // 1 v 6
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(4))); // 2 v 5
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(3))); // 3 v 4

        // Round 2
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(4))); // 1 v 5
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(3))); // 2 v 4
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(5))); // 3 v 6

        // Round 3
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(3))); // 1 v 4
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(2))); // 2 v 3
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(4)), players.get(teamColoursInCurrentOrder.get(5))); // 5 v 6

        // Round 4
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(2))); // 1 v 3
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(5))); // 2 v 6
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(3)), players.get(teamColoursInCurrentOrder.get(4))); // 4 v 5

        // Round 5
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(1))); // 1 v 2
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(4))); // 3 v 5
        matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(3)), players.get(teamColoursInCurrentOrder.get(5))); // 4 v 6
    }

}

package Game.Season;

import Player.Player;
import java.util.List;
import java.util.Map;

public class GameModes {
    PrintFunctionsForSeason printFunctionsForSeason = new PrintFunctionsForSeason();
    FootballMatches footballMatches = new FootballMatches();
    public void gameModeForTwoPlayers(Map<String, Player> players, List<String> teamColoursInCurrentOrder) throws InterruptedException {

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 1
        footballMatches.everyPlayerIsSIM(players); // SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 2
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(1))); // 1 v 2

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 3
        footballMatches.everyPlayerIsSIM(players); // SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 4
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(0))); // 2 v 1

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 5
        footballMatches.everyPlayerIsSIM(players); // SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
    }

    public void gameModeForThreePlayers(Map<String, Player> players, List<String> teamColoursInCurrentOrder) throws InterruptedException {

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 1
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(1))); // 1 v 2
        footballMatches.onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(2)));                                                   // 3 SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 2
        footballMatches.everyPlayerIsSIM(players);                                                                                         // SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 3
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(2))); // 1 v 3
        footballMatches.onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(1)));                                                   // 2 SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 4
        footballMatches.everyPlayerIsSIM(players);                                                                                         // SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 5
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(2))); // 2 v 3
        footballMatches.onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(0)));                                                   // 1 SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
    }

    public void gameModeForFourPlayers(Map<String, Player> players, List<String> teamColoursInCurrentOrder) throws InterruptedException {

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 1
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(3))); // 1 v 4
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(2))); // 2 v 3

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 2
        footballMatches.everyPlayerIsSIM(players);                                                                                         // SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 3
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(2))); // 1 v 3
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(3))); // 2 v 4

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 4
        footballMatches.everyPlayerIsSIM(players);                                                                                         // SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 5
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(1))); // 1 v 2
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(3))); // 3 v 4

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
    }

    public void gameModeForFivePlayers(Map<String, Player> players, List<String> teamColoursInCurrentOrder) throws InterruptedException {

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 1
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(1))); // 1 v 2
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(3))); // 3 v 4
        footballMatches.onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(4)));                                                   // 5 SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 2
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(2))); // 1 v 3
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(4))); // 2 v 5
        footballMatches.onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(3)));                                                   // 4 SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 3
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(4))); // 1 v 5
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(3))); // 2 v 4
        footballMatches.onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(2)));                                                   // 3 SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 4
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(3))); // 1 v 4
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(4))); // 3 v 5
        footballMatches.onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(1)));                                                   // 2 SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 5
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(2))); // 2 v 3
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(3)), players.get(teamColoursInCurrentOrder.get(4))); // 4 v 5
        footballMatches.onlyOnePlayerSIM(players.get(teamColoursInCurrentOrder.get(0)));                                                   // 1 SIM

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
    }

    public void gameModeForSixPlayers(Map<String, Player> players, List<String> teamColoursInCurrentOrder) throws InterruptedException {

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 1
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(5))); // 1 v 6
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(4))); // 2 v 5
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(3))); // 3 v 4

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 2
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(4))); // 1 v 5
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(3))); // 2 v 4
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(5))); // 3 v 6

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 3
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(3))); // 1 v 4
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(2))); // 2 v 3
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(4)), players.get(teamColoursInCurrentOrder.get(5))); // 5 v 6

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 4
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(2))); // 1 v 3
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(1)), players.get(teamColoursInCurrentOrder.get(5))); // 2 v 6
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(3)), players.get(teamColoursInCurrentOrder.get(4))); // 4 v 5

        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
        // Round 5
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(0)), players.get(teamColoursInCurrentOrder.get(1))); // 1 v 2
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(2)), players.get(teamColoursInCurrentOrder.get(4))); // 3 v 5
        footballMatches.matchPlayerVsPlayer(players.get(teamColoursInCurrentOrder.get(3)), players.get(teamColoursInCurrentOrder.get(5))); // 4 v 6


        printFunctionsForSeason.printPlayersStartingPositionsAndCurrentPoints(players,teamColoursInCurrentOrder);
    }

}

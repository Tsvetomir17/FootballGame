package Season;

import Player.Player;

import java.util.Map;

public class FootballMatches {

    public static void everyPlayerIsSIM(Map<String, Player> players){
        for(Player player : players.values()){
            SimVariations.simulateMatch(player);
        }
    }

    public static void onlyOnePlayerSIM(Player player){
        SimVariations.simulateMatch(player);
    }

    public static void matchPlayerVsPlayer(Player player1, Player player2){
        // to add
    }
}

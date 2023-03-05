package GameModes;

import Player.Player;

import java.util.Map;

abstract class GameMode {

    public void everyPlayerIsSIM(Map<String, Player> players){
        for(Player player : players.values()){
            SimVariations.simulateMatch(player);
        }
    }

    public void onlyOnePlayerSIM(Player player){
        SimVariations.simulateMatch(player);
    }

    public void matchPlayerVsPlayer(Player player1, Player player2){
        // to add
    }
}

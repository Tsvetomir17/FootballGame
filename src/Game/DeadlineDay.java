package Game;

import FootballPlayer.FootballPlayer;
import Player.Player;

import java.util.*;

import static Game.Game.*;
import static GameModes.FinalVariables.MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE;

public class DeadlineDay {

    public static void deadlineDay(){

        int playersThatWillAppearOnDeadlineDay = getPlayersInTheGameSize() + 1;

        for (int i = 0; i < playersThatWillAppearOnDeadlineDay; i++) {

            FootballPlayer currentFootballPlayerOnDeadlineDay = getNextFootballPlayerForDeadlineDay();
            int currentToBid = currentFootballPlayerOnDeadlineDay.getDeadlineDayPrice();

            List<Player> playersThatBidForTheCurrentFootballPlayer =
                    goingToParticipateInTheBidding(currentFootballPlayerOnDeadlineDay,currentToBid);

            currentToBid += playersThatBidForTheCurrentFootballPlayer.size();
            biddingBetweenThePlayersThatParticipateInTheBidding(playersThatBidForTheCurrentFootballPlayer,
                    currentFootballPlayerOnDeadlineDay,currentToBid);
        }
    }

    private static FootballPlayer getNextFootballPlayerForDeadlineDay(){

        return theFullDeckOfFootballPlayers.pop();
    }

    private static void biddingBetweenThePlayersThatParticipateInTheBidding
            (List<Player> playersThatBidForTheCurrentFootballPlayer,
            FootballPlayer footballPlayer,
            int currentToBid)
    {
        if(!playersThatBidForTheCurrentFootballPlayer.isEmpty()){

            while(playersThatBidForTheCurrentFootballPlayer.size() != 1){
                for (int i = 0; i < playersThatBidForTheCurrentFootballPlayer.size(); i++) {
                    if(playersThatBidForTheCurrentFootballPlayer.size() == 1) break;

                    if(playerChoiceToBidOrSkip(playersThatBidForTheCurrentFootballPlayer.get(i),footballPlayer,currentToBid)){
                        currentToBid++;
                    }else{
                        playersThatBidForTheCurrentFootballPlayer.remove(i);
                        i--;
                    }
                }
            }

            purchasePlayer(playersThatBidForTheCurrentFootballPlayer.get(0),footballPlayer,currentToBid-1);
        }
    }

    private static void purchasePlayer(Player player, FootballPlayer footballPlayer, int price){
        player.addFootballPlayerToTheTeamViaDeadlineDay(footballPlayer,price);
        System.out.println(player.getPlayerColour());
        System.out.println("You have successfully purchased " + footballPlayer.getFootballPlayerName());
        System.out.println();
        System.out.println();
    }
    private static List<Player> goingToParticipateInTheBidding(FootballPlayer footballPlayer, int currentToBid){
        List<Player> playersThatBidForTheCurrentFootballPlayer = new ArrayList<>();
        for (int j = 0; j < getPlayersInTheGameSize(); j++) {
            Player currentPlayer = players.get(teamColoursInCurrentOrder.get(j));
            if(playerChoiceToBidOrSkip(currentPlayer,footballPlayer,currentToBid)){
                playersThatBidForTheCurrentFootballPlayer.add(currentPlayer);
                currentToBid++;
            }
        }

        return playersThatBidForTheCurrentFootballPlayer;
    }
    private static boolean playerChoiceToBidOrSkip(Player currentPlayer, FootballPlayer footballPlayer, int currentToBid){
        if(!canThePlayerBidForThisFootballPlayer(currentPlayer,currentToBid)){
            return false;
        }
        printCurrentPlayerWithBidMessage(footballPlayer,currentToBid, currentPlayer);

        int choiceByUser = choiceMadeByTheUserValidation(0,2);
        return choiceByUser != 2;
    }

    private static boolean canThePlayerBidForThisFootballPlayer(Player currentPlayer, int currentToBid){
        if(currentPlayer.getPlayerMoney() < currentToBid){
            System.out.println();
            System.out.println(currentPlayer.getPlayerColour() + ", you do not have enough money to bid here");
            return false;
        }
        if(currentPlayer.getFullTeam().getFullTeamSize() == MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE){
            System.out.println();
            System.out.println(currentPlayer.getPlayerColour() + ", you can not add more players right now");
            return false;
        }

        return true;
    }

    private static void printCurrentPlayerWithBidMessage(FootballPlayer footballPlayer, int currentToBid, Player currentPlayer){
        System.out.println();
        System.out.println(currentPlayer.getPlayerColour() + ", it is your turn to bid for this player!");
        System.out.println("You have " + currentPlayer.getPlayerMoney() + "M.");
        footballPlayer.printFootballPlayerAsCard();
        System.out.println("Next bid: " + currentToBid + "M.");
        System.out.println("Choose one of the following:");
        System.out.println("    1. Bid");
        System.out.println("    2. Skip");
        System.out.println();
    }

}

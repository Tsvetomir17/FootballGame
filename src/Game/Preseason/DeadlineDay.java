package Game.Preseason;

import FootballPlayer.FootballPlayer;
import Game.Game;
import Player.Player;
import Game.InputValidator;
import java.sql.SQLException;
import java.util.*;
import static Game.FinalVariables.MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE;

public class DeadlineDay implements PreSeasonActions{

    @Override
    public void action(Game game) throws SQLException, ClassNotFoundException, InterruptedException {

        int playersThatWillAppearOnDeadlineDay = game.getPlayersInTheGameSize() + 1;

        for (int i = 0; i < playersThatWillAppearOnDeadlineDay; i++) {

            FootballPlayer currentFootballPlayerOnDeadlineDay = game.getTheTopPlayerFromTheDeck();
            currentFootballPlayerOnDeadlineDay.printFootballPlayerAsCard();
            int currentToBid = currentFootballPlayerOnDeadlineDay.getDeadlineDayPrice();

            List<Player> playersThatBidForTheCurrentFootballPlayer =
                    goingToParticipateInTheBidding(game, currentFootballPlayerOnDeadlineDay, currentToBid);

            currentToBid += playersThatBidForTheCurrentFootballPlayer.size();
            biddingBetweenThePlayersThatParticipateInTheBidding(playersThatBidForTheCurrentFootballPlayer,
                    currentFootballPlayerOnDeadlineDay,currentToBid);
        }
    }

    private static void biddingBetweenThePlayersThatParticipateInTheBidding
            (List<Player> playersThatBidForTheCurrentFootballPlayer,
            FootballPlayer footballPlayer,
            int currentToBid) {

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
        System.out.println("You have successfully purchased " + footballPlayer.getFootballPlayerName() + " for " + price +  "M.");
        System.out.println();
        System.out.println();
    }
    private static List<Player> goingToParticipateInTheBidding(Game game, FootballPlayer footballPlayer, int currentToBid){

        List<Player> playersThatBidForTheCurrentFootballPlayer = new ArrayList<>();
        for (int j = 0; j < game.getPlayersInTheGameSize(); j++) {
            Player currentPlayer = game.getPlayers().get(game.getTeamColoursInCurrentOrder().get(j));
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

        int choiceByUser = InputValidator.choiceMadeByTheUserValidation(1,2);
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

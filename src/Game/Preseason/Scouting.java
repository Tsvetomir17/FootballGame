package Game.Preseason;

import FootballPlayer.FootballPlayer;
import Game.Game;
import Player.Player;
import java.sql.SQLException;
import static Game.FinalVariables.MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE;
import Game.InputValidator;

public class Scouting implements PreSeasonActions{
    @Override
    public void action(Game game) throws SQLException, ClassNotFoundException, InterruptedException {
        for (int i = 0; i < game.getPlayersInTheGameSize(); i++) {

            Player currentPlayer = game.getPlayers().get(game.getTeamColoursInCurrentOrder().get(i));

            int playersThatCanBeScoutedSize = currentPlayer.getPlayerUpgradeBoard().getScoutingStaffLevel();
            System.out.println(currentPlayer.getPlayerColour() +", it is your turn to do scouting!");
            System.out.println("You can scout "+ playersThatCanBeScoutedSize + " players.");

            while(playersThatCanBeScoutedSize > 0){

                System.out.println("You have " + currentPlayer.getPlayerMoney() + "M.");
                playersThatCanBeScoutedSize--;
                if (!validateIfThePlayerCanAddFootballPlayer(currentPlayer)){

                    System.out.println("Your team is full, you can not add more players right now");
                    break;
                }

                FootballPlayer scoutedPlayer = game.getTheTopPlayerFromTheDeck();
                scoutedPlayer.printFootballPlayerAsCard();

                if(!checkIfThePlayerHasEnoughMoney(currentPlayer,scoutedPlayer)) continue;

                if(choiceMadeByUser() == 1){

                    buyPlayer(currentPlayer,scoutedPlayer);
                }
            }
        }
    }

    private static boolean validateIfThePlayerCanAddFootballPlayer(Player player){

        return player.getFullTeam().getFullTeamSize() < MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE;
    }

    private static int choiceMadeByUser(){

        System.out.println("1. Buy");
        System.out.println("2. Skip");

        return InputValidator.choiceMadeByTheUserValidation(1,2);
    }

    private static void buyPlayer(Player currentPlayer, FootballPlayer scoutedPlayer){

        currentPlayer.addFootballPlayerToTheTeamViaScouting(scoutedPlayer);
        System.out.println("You successfully bought " + scoutedPlayer.getFootballPlayerName() + " for " + scoutedPlayer.getScoutingPrice() + "M. !");
    }

    private static boolean checkIfThePlayerHasEnoughMoney(Player currentPlayer, FootballPlayer scoutedPlayer){

        if(currentPlayer.getPlayerMoney() < scoutedPlayer.getScoutingPrice()){

            System.out.println("You do not have enough money for this player");
            return false;
        }
        return true;
    }
}

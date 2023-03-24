package Game.Preseason;

import Game.Game;
import Player.Player;
import Game.InputValidator;
import java.sql.SQLException;

public class Discarding implements PreSeasonActions{

    @Override
    public void action(Game game) throws SQLException, ClassNotFoundException, InterruptedException {

        for (int i = 0; i < game.getPlayersInTheGameSize(); i++) {

            Player currentPlayer = game.getPlayers().get(game.getTeamColoursInCurrentOrder().get(i));
            printStartingMessage(currentPlayer);
            int choiceByPlayer = InputValidator.choiceMadeByTheUserValidation(0,currentPlayer.getFullTeam().getFullTeamSize());
            while (choiceByPlayer != 0){

                removeFootballPlayerAtIndex(choiceByPlayer, currentPlayer);
                printStartingMessage(currentPlayer);
                choiceByPlayer = InputValidator.choiceMadeByTheUserValidation(0,currentPlayer.getFullTeam().getFullTeamSize());
            }
        }
    }

    private static void printStartingMessage(Player currentPlayer){

        System.out.println(currentPlayer.getPlayerColour() + ", it is your turn to discard.");
        System.out.println("You can choose a player to be discarded or press '0' to skip this phase");
        System.out.println("0. Skip");
        currentPlayer.printFullTeam();
        System.out.println("Choose a player, if you want to discard him: ");

    }

    private static void removeFootballPlayerAtIndex(int index, Player player){

        if(index < 0 || index > player.getFullTeam().getFullTeamSize()){

            throw new ArrayIndexOutOfBoundsException();
        }

        if(player.getFullTeam().getFullTeamSize() <= 11){

            System.out.println("You can not discard more players, because you cant have less than 11 players");
            return;
        }

        player.addMoneyToThePlayer(player.getFullTeam().getFootballPlayerAtIndex(index-1).getScoutingPrice());
        player.discardFootballPlayerFromTheTeam(index-1);
    }
}

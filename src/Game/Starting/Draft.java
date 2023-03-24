package Game.Starting;

import FootballPlayer.FootballPlayer;
import Game.Game;
import Player.Player;

import java.util.ArrayList;
import java.util.List;
import Game.InputValidator;

import static Game.FinalVariables.DRAFT_SIZE;

public class Draft implements StartingAction{

    @Override
    public void action(Game game) {

        List<FootballPlayer> currentPlayersInTheDraft;
        int currentIndexForTheTeamToPick = 0;

        for (int i = 0; i < game.getPlayersInTheGameSize(); i++) {

            currentPlayersInTheDraft = setPlayersToBeDrafted(game);

            while (!currentPlayersInTheDraft.isEmpty()) {

                printFootballPlayers(currentPlayersInTheDraft);

                playerToPickFootballPlayer(game.getPlayers().get(game.getTeamColoursInCurrentOrder().get(currentIndexForTheTeamToPick)), currentPlayersInTheDraft);
                currentIndexForTheTeamToPick = setCurrentIndexForTeamToPick(currentIndexForTheTeamToPick, game.getPlayersInTheGameSize());
            }
        }
    }

    private List<FootballPlayer> setPlayersToBeDrafted(Game game){

        List<FootballPlayer> currentPlayersInTheDraft = new ArrayList<>();
        for (int j = 0; j < DRAFT_SIZE; j++) {

            currentPlayersInTheDraft.add(game.getTheTopPlayerFromTheDeck());
        }

        return currentPlayersInTheDraft;
    }

    private void printFootballPlayers(List<FootballPlayer> currentPlayersInTheDraft) {

        int iterator = 1;
        for (FootballPlayer player : currentPlayersInTheDraft) {

            System.out.print((iterator++) + ". ");
            player.printFootballPlayer();
        }
    }

    private void playerToPickFootballPlayer(Player player, List<FootballPlayer> currentPlayersInTheDraft){

        System.out.println(player.getPlayerColour() + ", it is your turn to pick: ");
        int choiceByPlayer = InputValidator.choiceMadeByTheUserValidation(1, currentPlayersInTheDraft.size()) -1;
        player.addFootballPlayerToTheTeamViaDraft(currentPlayersInTheDraft.get(choiceByPlayer));

        currentPlayersInTheDraft.remove(choiceByPlayer);
    }


    private int setCurrentIndexForTeamToPick(int index, int playersInTheGameSize){

        return ++index == playersInTheGameSize ? 0 : index;
    }
}

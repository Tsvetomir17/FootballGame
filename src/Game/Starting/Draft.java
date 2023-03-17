package Game.Starting;

import FootballPlayer.FootballPlayer;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

import static Game.Game.*;
import static Game.FinalVariables.DRAFT_SIZE;

public class Draft {

    public static void setTheFootballPlayersDraft(){
        List<FootballPlayer> currentPlayersInTheDraft;
        int currentIndexForTheTeamToPick = 0;

        for (int i = 0; i < getPlayersInTheGameSize(); i++) {

            currentPlayersInTheDraft = setPlayersToBeDrafted();

            while (!currentPlayersInTheDraft.isEmpty()) {

                printFootballPlayers(currentPlayersInTheDraft);

                playerToPickFootballPlayer(getPlayers().get(getTeamColoursInCurrentOrder().get(currentIndexForTheTeamToPick)),
                        currentPlayersInTheDraft);
                currentIndexForTheTeamToPick = setCurrentIndexForTeamToPick(currentIndexForTheTeamToPick);
            }
        }
    }

    private static List<FootballPlayer> setPlayersToBeDrafted(){
        List<FootballPlayer> currentPlayersInTheDraft = new ArrayList<>();
        for (int j = 0; j < DRAFT_SIZE; j++) {
            currentPlayersInTheDraft.add(getTheTopPlayerFromTheDeck());
        }

        return currentPlayersInTheDraft;
    }

    private static void printFootballPlayers(List<FootballPlayer> currentPlayersInTheDraft) {
        int iterator = 1;
        for (FootballPlayer player : currentPlayersInTheDraft) {
            System.out.print((iterator++) + ". ");
            player.printFootballPlayer();
        }
    }

    private static void playerToPickFootballPlayer(Player player, List<FootballPlayer> currentPlayersInTheDraft){
        System.out.println(player.getPlayerColour() + ", it is your turn to pick: ");
        int choiceByPlayer = choiceMadeByTheUserValidation(0, currentPlayersInTheDraft.size()) -1;
        player.addFootballPlayerToTheTeamViaDraft(currentPlayersInTheDraft.get(choiceByPlayer));

        currentPlayersInTheDraft.remove(choiceByPlayer);
    }


    private static int setCurrentIndexForTeamToPick(int index){
        return ++index == getPlayersInTheGameSize() ? 0 : index;
    }
}

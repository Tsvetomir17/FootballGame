package Game;

import FootballPlayer.FootballPlayer;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

import static Game.Game.*;
import static Game.FinalVariables.DRAFT_SIZE;

public class Draft {

    public static void setTheFootballPlayersDraft(){
        List<FootballPlayer> currentPlayersInTheDraft = new ArrayList<>();
        int currentIndexForTheTeamToPick = 0;
        Player currentPlayerToPick;
        for (int i = 0; i < getPlayersInTheGameSize(); i++) {

            currentPlayersInTheDraft = setPlayersToBeDrafted();

            while (!currentPlayersInTheDraft.isEmpty()) {

                printFootballPlayersAsCards(currentPlayersInTheDraft);

                currentPlayerToPick = players.get(teamColoursInCurrentOrder.get(currentIndexForTheTeamToPick));
                System.out.println(currentPlayerToPick.getPlayerColour() + ", it is your turn to pick: ");
                //int choiceByPlayer = choiceMadeByTheUserValidation(0, currentPlayersInTheDraft.size()) -1;
                int choiceByPlayer = 0;
                currentPlayerToPick.addFootballPlayerToTheTeamViaDraft(currentPlayersInTheDraft.get(choiceByPlayer));

                currentPlayersInTheDraft.remove(choiceByPlayer);
                currentIndexForTheTeamToPick = setCurrentIndexForTeamToPick(currentIndexForTheTeamToPick);
            }
        }
    }

    private static List<FootballPlayer> setPlayersToBeDrafted(){
        List<FootballPlayer> currentPlayersInTheDraft = new ArrayList<>();
        for (int j = 0; j < DRAFT_SIZE; j++) {
            currentPlayersInTheDraft.add(theFullDeckOfFootballPlayers.pop());
        }

        return currentPlayersInTheDraft;
    }

    private static int setCurrentIndexForTeamToPick(int index){
        return ++index == getPlayersInTheGameSize() ? 0 : index;
    }
}

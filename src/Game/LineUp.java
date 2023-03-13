package Game;

import FootballPlayer.FootballPlayer;
import FootballPlayer.FootballPlayersPositions;
import Player.FullTeam;
import Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Game.Game.*;
import static GameModes.FinalVariables.*;


public class LineUp {

    public static void setLineUp(){

        for (int i = 0; i < getPlayersInTheGameSize(); i++) {
            Player currentPlayer = players.get(teamColoursInCurrentOrder.get(i));
            printStartingMessage(currentPlayer);
            playerChoosingFormation(currentPlayer);
        }
    }

    private static void printStartingMessage(Player currentPlayer){
        System.out.println(currentPlayer.getPlayerColour() + ", choose formation for the next season");
        currentPlayer.getFullTeam().printFullTeam();
        printFormations();
    }

    private static void printFormations(){
        System.out.println("1. 3-5-2");
        System.out.println("2. 3-4-3");
        System.out.println("3. 3-3-4");
        System.out.println("4. 4-4-2");
        System.out.println("5. 4-3-3");
    }

    private static int playerChoiceForFormation(Player currentPlayer){
        return choiceMadeByTheUserValidation(0,5);
    }

    private static void playerChoosingFormation(Player currentPlayer){
        int choice = playerChoiceForFormation(currentPlayer);

        switch (choice) {
            case 1 -> choosingStartingEleven(currentPlayer, 3, 5, 2);
            case 2 -> choosingStartingEleven(currentPlayer, 3, 4, 3);
            case 3 -> choosingStartingEleven(currentPlayer, 3, 3, 4);
            case 4 -> choosingStartingEleven(currentPlayer, 4, 4, 2);
            default -> choosingStartingEleven(currentPlayer, 4, 3, 3);
        }
    }

    private static void choosingStartingEleven(Player currentPlayer, int defSize, int midSize, int attSize){
        FullTeam playerSquad = currentPlayer.getFullTeam();
        List<FootballPlayer> defenceRow = new ArrayList<FootballPlayer>();
        List<FootballPlayer> midfieldRow = new ArrayList<FootballPlayer>();
        List<FootballPlayer> attackRow = new ArrayList<FootballPlayer>();

        System.out.println("Goalkeeper: ");
        int goalkeeperRating = setGoalkeeperForTheLineUp(playerSquad);

        System.out.println("Defence: ");
        currentPlayer.getFullTeam().setDefenceOverall(goalkeeperRating +
                fillOneLineWithPlayersAndReturnStats(playerSquad,defSize,FootballPlayersPositions.DEFENDER,defenceRow));

        System.out.println("Midfield: ");
        currentPlayer.getFullTeam().setMidfieldOverall(fillOneLineWithPlayersAndReturnStats(playerSquad,midSize,
                FootballPlayersPositions.MIDFIELDER,midfieldRow));

        System.out.println("Attack: ");
        currentPlayer.getFullTeam().setAttackOverall(fillOneLineWithPlayersAndReturnStats(playerSquad,attSize,
                FootballPlayersPositions.ATTACKER,attackRow));
    }

    private static void printChosenTeam(List<FootballPlayer> defenceRow, List<FootballPlayer> midfiledRow, List<FootballPlayer> attackRow){

    }
    private static double fillOneLineWithPlayersAndReturnStats(FullTeam playerSquad, int lineSize,
                                                               FootballPlayersPositions position, List<FootballPlayer> currentRow){

        for (int i = 0; i < lineSize; i++) {
            playerSquad.printFullTeam();
            int indexOfSelectedFootballPlayer = choiceMadeByTheUserValidation(0,playerSquad.getFullTeamSize()) -1;

            while(Objects.equals(playerSquad.getFootballPlayerAtIndex(indexOfSelectedFootballPlayer).getFootballPlayerPositionAsString(),
                    GOALKEEPER_STRING)){
                System.out.println("You can not add goalkeeper there");
                indexOfSelectedFootballPlayer = choiceMadeByTheUserValidation(0,playerSquad.getFullTeamSize()) -1;
            }

            currentRow.add(playerSquad.selectFootballPlayerForLineUpAtIndex(indexOfSelectedFootballPlayer));
        }

        return calculateLineOverall(currentRow, position);
    }

    private static double calculateLineOverall(List<FootballPlayer> playerSquad, FootballPlayersPositions position){
        int result = 0;
        for (int i = 1; i < playerSquad.size(); i++) {

            if(playerSquad.get(i).isHasLeftChemistryStyle() && playerSquad.get(i-1).isHasRightChemistryStyle()){
                result++;
            }

            result += getFootballPlayersRatingOnCurrentPosition(playerSquad.get(i), position);
        }

        return result + getFootballPlayersRatingOnCurrentPosition(playerSquad.get(0), position);
    }

    private static double getFootballPlayersRatingOnCurrentPosition(FootballPlayer player, FootballPlayersPositions position){
        if(Objects.equals(player.getFootballPlayerPositionAsString(), position.toString()) ||
                Objects.equals(player.getFootballPlayerPositionAsString(), position.toString())){
            return player.getCurrentFootballPlayerRating();
        }
        else if((Objects.equals(player.getFootballPlayerPositionAsString(), ATTACKER_STRING) &&
                Objects.equals(position.toString(), DEFENDER_STRING)) ||
                Objects.equals(player.getFootballPlayerPositionAsString(), DEFENDER_STRING) &&
                        Objects.equals(position.toString(), ATTACKER_STRING)){
            return player.getCurrentFootballPlayerRating()-1;
        }
        else return player.getCurrentFootballPlayerRating()-0.5;
    }

    private static int setGoalkeeperForTheLineUp(FullTeam playerSquad){
        if(hasThePlayerAGoalkeeperInTheTeam(playerSquad)){
            System.out.println("Choose your goalkeeper: ");
            int playerChoice = choiceMadeByTheUserValidation(0,playerSquad.getFullTeamSize())-1;

            while(!Objects.equals(playerSquad.getFootballPlayerAtIndex(playerChoice).getFootballPlayerPositionAsString(),
                    GOALKEEPER_STRING)){

                System.out.println("Choose a goalkeeper: ");
                playerChoice = choiceMadeByTheUserValidation(0,playerSquad.getFullTeamSize())-1;
            }

            return playerSquad.getFootballPlayerAtIndex(playerChoice).getMaxFootballPlayerRating();
        }

        System.out.println("You do not have goalkeeper in your squad. You will have +1 in defence");
        return 1;
    }

    private static boolean hasThePlayerAGoalkeeperInTheTeam(FullTeam playerSquad){
        for (int i = 0; i < playerSquad.getFullTeamSize(); i++) {
            if(Objects.equals(playerSquad.getFootballPlayerAtIndex(i).getFootballPlayerPositionAsString(),
                    GOALKEEPER_STRING))
                return true;
        }
        return false;
    }
}

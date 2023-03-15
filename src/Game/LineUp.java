package Game;

import FootballPlayer.FootballPlayer;
import FootballPlayer.FootballPlayersPositions;
import Player.FullTeam;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

import static Game.Game.*;

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

    private static int playerChoiceForFormation(){
        return choiceMadeByTheUserValidation(0,5);
    }

    private static void playerChoosingFormation(Player currentPlayer){
        int choice = playerChoiceForFormation();

        switch (choice) {
            case 1 -> choosingStartingEleven(currentPlayer, 3, 5, 2);
            case 2 -> choosingStartingEleven(currentPlayer, 3, 4, 3);
            case 3 -> choosingStartingEleven(currentPlayer, 3, 3, 4);
            case 4 -> choosingStartingEleven(currentPlayer, 4, 4, 2);
            default -> choosingStartingEleven(currentPlayer, 4, 3, 3);
        }
    }

    private static void choosingStartingEleven(Player currentPlayer, int defSize, int midSize, int attSize){
        FullTeam playerSquad = new FullTeam(currentPlayer.getFullTeam());
        List<FootballPlayer> defenceRow = new ArrayList<>();
        List<FootballPlayer> midfieldRow = new ArrayList<>();
        List<FootballPlayer> attackRow = new ArrayList<>();

        fillLinesInLineUp(playerSquad,defSize,midSize,attSize,defenceRow,midfieldRow,attackRow);

        makeFinalChangesInTheLineUp(playerSquad, defenceRow, midfieldRow, attackRow);

        currentPlayer.getFullTeam().setDefenceOverall(calculateLineOverall(defenceRow, FootballPlayersPositions.DEFENDER));
        currentPlayer.getFullTeam().setMidfieldOverall(calculateLineOverall(midfieldRow, FootballPlayersPositions.MIDFIELDER));
        currentPlayer.getFullTeam().setAttackOverall(calculateLineOverall(attackRow, FootballPlayersPositions.ATTACKER));

        setCaptainBoost(currentPlayer,defenceRow,midfieldRow,attackRow);
    }

    private static void fillLinesInLineUp(FullTeam playerSquad, int defSize, int midSize, int attSize, List<FootballPlayer> defenceRow, List<FootballPlayer> midfieldRow, List<FootballPlayer> attackRow){
        System.out.println("Goalkeeper: ");
        FootballPlayer currentGoalkeeper = setGoalkeeperForTheLineUp(playerSquad);

        System.out.println("Defence: ");
        fillOneLineWithPlayersAndReturnStats(playerSquad,defSize,defenceRow);
        defenceRow.add(0,currentGoalkeeper);

        System.out.println("Midfield: ");
        fillOneLineWithPlayersAndReturnStats(playerSquad,midSize,midfieldRow);

        System.out.println("Attack: ");
        fillOneLineWithPlayersAndReturnStats(playerSquad,attSize,attackRow);
    }

    private static void printRowFromTheLineUp(List<FootballPlayer> currentRow){
        int currentIndex = 1;
        for (FootballPlayer player : currentRow) {
            System.out.print(currentIndex++ + ". ");
            player.printFootballPlayer();
        }
        System.out.println();
    }

    private static void printCurrentLineUp(List<FootballPlayer> defenceRow, List<FootballPlayer> midfieldRow, List<FootballPlayer> attackRow) {
        System.out.println("Defence:");
        printRowFromTheLineUp(defenceRow);
        System.out.println("Midfield: ");
        printRowFromTheLineUp(midfieldRow);
        System.out.println("Attack: ");
        printRowFromTheLineUp(attackRow);

    }
    private static void fillOneLineWithPlayersAndReturnStats(FullTeam playerSquad, int lineSize, List<FootballPlayer> currentRow){

        for (int i = 0; i < lineSize; i++) {
            playerSquad.printFullTeam();
            int indexOfSelectedFootballPlayer = choiceMadeByTheUserValidation(0,playerSquad.getFullTeamSize()) -1;

            while(playerSquad.getFootballPlayerAtIndex(indexOfSelectedFootballPlayer).getFootballPlayerPosition() == FootballPlayersPositions.GOALKEEPER){
                System.out.println("You can not add goalkeeper there");
                indexOfSelectedFootballPlayer = choiceMadeByTheUserValidation(0,playerSquad.getFullTeamSize()) -1;
            }

            currentRow.add(playerSquad.selectFootballPlayerForLineUpAtIndex(indexOfSelectedFootballPlayer));
        }
    }

    private static double calculateLineOverall(List<FootballPlayer> playerSquad, FootballPlayersPositions position){
        double result = 0;
        for (int i = 1; i < playerSquad.size(); i++) {

            if(playerSquad.get(i).isHasLeftChemistryStyle() && playerSquad.get(i-1).isHasRightChemistryStyle()){
                result++;
            }

            result += getFootballPlayersRatingOnCurrentPosition(playerSquad.get(i), position);
        }
        return result + getFootballPlayersRatingOnCurrentPosition(playerSquad.get(0), position);
    }

    private static double getFootballPlayersRatingOnCurrentPosition(FootballPlayer player, FootballPlayersPositions position){
        if( player.getFootballPlayerPosition() == position ||
            player.getFootballPlayerPosition() == FootballPlayersPositions.PURPLE ||
            player.getFootballPlayerPosition() == FootballPlayersPositions.GOALKEEPER){
            return player.getCurrentFootballPlayerRating();
        }
        else if((player.getFootballPlayerPosition() == FootballPlayersPositions.ATTACKER && position == FootballPlayersPositions.DEFENDER) ||
                (player.getFootballPlayerPosition() == FootballPlayersPositions.DEFENDER && position == FootballPlayersPositions.ATTACKER)){
            return player.getCurrentFootballPlayerRating()-1;
        }
        else return player.getCurrentFootballPlayerRating()-0.5;
    }

    private static FootballPlayer setGoalkeeperForTheLineUp(FullTeam playerSquad){
        if(hasThePlayerAGoalkeeperInTheTeam(playerSquad)){
            System.out.println("Choose your goalkeeper: ");
            int playerChoice = choiceMadeByTheUserValidation(0,playerSquad.getFullTeamSize())-1;
            while(!(playerSquad.getFootballPlayerAtIndex(playerChoice).getFootballPlayerPosition() == FootballPlayersPositions.GOALKEEPER)){
                System.out.println("Choose a goalkeeper: ");
                System.out.println("(Note: You can not change the goalkeeper after that choice!)");
                playerChoice = choiceMadeByTheUserValidation(0,playerSquad.getFullTeamSize())-1;
            }

            return playerSquad.selectFootballPlayerForLineUpAtIndex(playerChoice);
        }

        System.out.println("You do not have goalkeeper in your squad. You will be granted 1 star goalkeeper");
        return new FootballPlayer("Goalie", 1,1,"GOALKEEPER",false,false,false,0,0);
    }

    private static boolean hasThePlayerAGoalkeeperInTheTeam(FullTeam playerSquad){
        for (int i = 0; i < playerSquad.getFullTeamSize(); i++) {
            if(playerSquad.getFootballPlayerAtIndex(i).getFootballPlayerPosition() == FootballPlayersPositions.GOALKEEPER)
                return true;
        }
        return false;
    }

    private static void makeFinalChangesInTheLineUp(FullTeam playerSquad, List<FootballPlayer> defenceRow, List<FootballPlayer> midfieldRow, List<FootballPlayer> attackRow){
        printCurrentLineUp(defenceRow, midfieldRow, attackRow);
        printFirstMessageAskingForChangesInLineUp();

        int choiceByUser = choiceMadeByTheUserValidation(0,2);
        if(choiceByUser == 2) return;

        printMessageChoosingLineForChange();
        choiceByUser = choiceMadeByTheUserValidation(0,3);

        switch (choiceByUser) {
            case 1 -> makeChangesInRow(playerSquad, defenceRow);
            case 2 -> makeChangesInRow(playerSquad, midfieldRow);
            default -> makeChangesInRow(playerSquad, attackRow);
        }

        makeFinalChangesInTheLineUp(playerSquad,defenceRow,midfieldRow,attackRow);
    }

    private static void printFirstMessageAskingForChangesInLineUp(){
        System.out.println("Do you want to make changes to this starting line up: ");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }

    private static void printMessageChoosingLineForChange(){
        System.out.println("Choose a line from the team where you want to make the changes:");
        System.out.println("1. Defence");
        System.out.println("2. Midfield");
        System.out.println("3. Attack");
    }

    private static void makeChangesInRow(FullTeam playerSquad, List<FootballPlayer> currentRow){

        printRowFromTheLineUp(currentRow);
        System.out.println("Choose player to be changed: ");
        int choiceByUserForTheFirstPlayer = playersChoiceValidationWithAdditionThatTheFootballPlayerIsNotGoalkeeper(currentRow);

        playerSquad.printFullTeam();
        System.out.println("Choose player to start the game: ");
        int choiceByUserForTheSecondPlayer = playersChoiceValidationWithAdditionThatTheFootballPlayerIsNotGoalkeeper(playerSquad.getFullTeamOfFootballPlayers());

        swapFootballPlayersFromStartingLineUpAndTheBench(playerSquad,choiceByUserForTheFirstPlayer,choiceByUserForTheSecondPlayer,currentRow);
    }

    private static void swapFootballPlayersFromStartingLineUpAndTheBench(FullTeam playerSquad, int choiceByUserForTheFirstPlayer, int choiceByUserForTheSecondPlayer, List<FootballPlayer> currentRow){
        FootballPlayer tempPlayer = currentRow.get(choiceByUserForTheFirstPlayer);
        currentRow.remove(choiceByUserForTheFirstPlayer);
        currentRow.add(choiceByUserForTheFirstPlayer, playerSquad.selectFootballPlayerForLineUpAtIndex(choiceByUserForTheSecondPlayer));
        playerSquad.addPlayerToTheTeam(tempPlayer);
    }

    private static int playersChoiceValidationWithAdditionThatTheFootballPlayerIsNotGoalkeeper(List<FootballPlayer> currentPlayers){
        int choiceByUser = choiceMadeByTheUserValidation(0, currentPlayers.size()) -1;
        boolean checkIfGoalkeeper = currentPlayers.get(choiceByUser).getFootballPlayerPosition() == FootballPlayersPositions.GOALKEEPER;
        while (checkIfGoalkeeper){
            System.out.println("You can not choose goalkeepers in that phase of the game");
            choiceByUser = choiceMadeByTheUserValidation(0, currentPlayers.size()) -1;
            checkIfGoalkeeper = currentPlayers.get(choiceByUser).getFootballPlayerPosition() == FootballPlayersPositions.GOALKEEPER;
        }

        return choiceByUser;
    }

    private static boolean isThereACaptainForFullBoost(List<FootballPlayer> currentRow, FootballPlayersPositions position){
        for (FootballPlayer player : currentRow) {
            if (player.isCaptain() && (player.getFootballPlayerPosition() == position ||
                                       player.getFootballPlayerPosition() == FootballPlayersPositions.GOALKEEPER ||
                                       player.getFootballPlayerPosition() == FootballPlayersPositions.PURPLE)) {
                return true;
            }
        }

        return false;
    }

    private static double getCaptainBoostOnTheCurrentRow(Player currentPlayer, List<FootballPlayer> currentRow, FootballPlayersPositions positions){

        return isThereACaptainForFullBoost(currentRow,positions) ? currentPlayer.getCaptainBoost() : currentPlayer.getCaptainBoost() / 2;
    }

    private static void setCaptainBoost(Player currentPlayer, List<FootballPlayer> defenceRow, List<FootballPlayer> midfieldRow, List<FootballPlayer> attackRow){
        printMessageForCaptainBoost(currentPlayer);

        int choiceByUser = choiceMadeByTheUserValidation(0,3);
        switch (choiceByUser){
            case 1 -> currentPlayer.getFullTeam().setDefenceOverall(currentPlayer.getFullTeam().getDefenceOverall() + getCaptainBoostOnTheCurrentRow(currentPlayer,defenceRow,FootballPlayersPositions.DEFENDER));
            case 2 ->currentPlayer.getFullTeam().setMidfieldOverall(currentPlayer.getFullTeam().getMidfieldOverall() + getCaptainBoostOnTheCurrentRow(currentPlayer,midfieldRow,FootballPlayersPositions.MIDFIELDER));
            default -> currentPlayer.getFullTeam().setAttackOverall(currentPlayer.getFullTeam().getAttackOverall() + getCaptainBoostOnTheCurrentRow(currentPlayer,attackRow,FootballPlayersPositions.ATTACKER));
        }
    }

    private static void printMessageForCaptainBoost(Player currentPlayer){
        double captainBoost = currentPlayer.getCaptainBoost();
        System.out.println("Choose where to put your captain boost");
        System.out.println("Current captain boost: " + captainBoost);
        System.out.println("1. Defence");
        System.out.println("2. Midfield");
        System.out.println("3. Attack");
    }
}

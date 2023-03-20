package Game.AfterSeason;

import Player.Player;

import java.sql.*;
import java.util.List;
import java.util.Map;

import static Game.AfterSeason.PotentialWinning.checkIfWeHaveWinner;
import static Game.AfterSeason.PotentialWinning.printWinningMessage;
import static Game.AfterSeason.PotentialWinning.checkIfWeHavePotentialWinner;
import static Game.AfterSeason.PotentialWinning.choiceForUserToPlayTheFootballCup;
import static Game.AfterSeason.PotentialWinning.playFootballCupMatch;
import static Game.FinalVariables.FIRST_PLACE_IN_THE_SEASON_INCOME;
import static Game.FinalVariables.STEP_BEHIND_THE_FIRST_PLACE_FOR_SEASON_INCOME;
import static Game.Game.getPlayers;
import static Game.Game.getTeamColoursInCurrentOrder;


public class AfterSeason{
    public static boolean afterSeason() throws SQLException, ClassNotFoundException, InterruptedException {

        setTeamColoursInOrderAfterTheSeason(getTeamColoursInCurrentOrder());
        setTheBudgetOfEveryPlayerAfterTheSeason(getPlayers(),getTeamColoursInCurrentOrder());
        setCaptainBoostsAfterTheSeason(getPlayers(),getTeamColoursInCurrentOrder());
        giveAWinTokenToTheSeasonWinner(getPlayers(),getTeamColoursInCurrentOrder());

        if(checkIfWeHaveWinner()){

            printWinningMessage();
            return false;
        }

        if (checkIfWeHavePotentialWinner()){

            if(choiceForUserToPlayTheFootballCup() == 1){

                return !playFootballCupMatch();
            }
        }

        return true;
    }
    private static void setTheBudgetOfEveryPlayerAfterTheSeason(Map<String, Player> players, List<String> teamColoursInCurrentOrder){

        for (int steps = 0; steps < players.size(); steps++) {

            Player currentPlayer = players.get(teamColoursInCurrentOrder.get(steps));
            currentPlayer.addMoneyToThePlayer(FIRST_PLACE_IN_THE_SEASON_INCOME - steps * STEP_BEHIND_THE_FIRST_PLACE_FOR_SEASON_INCOME);
            currentPlayer.addMoneyToThePlayer(currentPlayer.getPlayerUpgradeBoard().getStadiumIncomeForTheCurrentPoints(currentPlayer.getCurrentPointsInTheSeason()));
            currentPlayer.spendMoney(currentPlayer.getCurrentPlayerOverall());
        }
    }

    private static void setTeamColoursInOrderAfterTheSeason(List<String> teamColoursInCurrentOrder){

        teamColoursInCurrentOrder.sort(new TeamsAfterSeasonComparator());
    }

    private static void setCaptainBoostsAfterTheSeason(Map<String,Player> players, List<String> teamColoursInCurrentOrder){

        for (int i = 0; i < players.size(); i++) {

            players.get(teamColoursInCurrentOrder.get(i)).setCaptainBoost(i+1);
        }
    }

    private static void giveAWinTokenToTheSeasonWinner(Map<String,Player> players, List<String> teamColoursInCurrentOrder){

        players.get(teamColoursInCurrentOrder.get(0)).addWinToThePlayer();
    }
}

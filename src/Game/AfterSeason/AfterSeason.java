package Game.AfterSeason;

import Game.Game;
import Player.Player;
import java.sql.*;
import java.util.List;
import java.util.Map;
import static Game.FinalVariables.FIRST_PLACE_IN_THE_SEASON_INCOME;
import static Game.FinalVariables.STEP_BEHIND_THE_FIRST_PLACE_FOR_SEASON_INCOME;

public class AfterSeason implements AfterSeasonAction{

    @Override
    public boolean action(Game game) throws SQLException, ClassNotFoundException, InterruptedException {

        setPlacementsAndBudgets(game);
        return checkIfWeHaveAWinner(game);
    }

    private void setPlacementsAndBudgets(Game game){

        setTeamColoursInOrderAfterTheSeason(game.getTeamColoursInCurrentOrder());
        setTheBudgetOfEveryPlayerAfterTheSeason(game.getPlayers(),game.getTeamColoursInCurrentOrder());
        setCaptainBoostsAfterTheSeason(game.getPlayers(),game.getTeamColoursInCurrentOrder());
        giveAWinTokenToTheSeasonWinner(game.getPlayers(),game.getTeamColoursInCurrentOrder());
    }

    private boolean checkIfWeHaveAWinner(Game game) throws SQLException, ClassNotFoundException, InterruptedException {

        PotentialWinning potentialWinning = new PotentialWinning();
        Player currentFirstPlayer = game.getPlayers().get(game.getTeamColoursInCurrentOrder().get(0));

        if(potentialWinning.checkIfWeHaveWinner(currentFirstPlayer)){

            potentialWinning.printWinningMessage(currentFirstPlayer);
            return false;
        }

        if (potentialWinning.checkIfWeHavePotentialWinner(currentFirstPlayer)){

            if(potentialWinning.choiceForUserToPlayTheFootballCup(currentFirstPlayer) == 1){

                return !potentialWinning.playFootballCupMatch(currentFirstPlayer);
            }
        }

        return true;
    }

    private void setTheBudgetOfEveryPlayerAfterTheSeason(Map<String, Player> players, List<String> teamColoursInCurrentOrder){

        for (int steps = 0; steps < players.size(); steps++) {

            Player currentPlayer = players.get(teamColoursInCurrentOrder.get(steps));
            currentPlayer.addMoneyToThePlayer(FIRST_PLACE_IN_THE_SEASON_INCOME - steps * STEP_BEHIND_THE_FIRST_PLACE_FOR_SEASON_INCOME);
            currentPlayer.addMoneyToThePlayer(currentPlayer.getPlayerUpgradeBoard().getStadiumIncomeForTheCurrentPoints(currentPlayer.getCurrentPointsInTheSeason()));
            currentPlayer.spendMoney(currentPlayer.getCurrentOverall());
        }
    }

    private void setTeamColoursInOrderAfterTheSeason(List<String> teamColoursInCurrentOrder){

        teamColoursInCurrentOrder.sort(new TeamsAfterSeasonComparator());
    }

    private void setCaptainBoostsAfterTheSeason(Map<String,Player> players, List<String> teamColoursInCurrentOrder){

        for (int i = 0; i < players.size(); i++) {

            players.get(teamColoursInCurrentOrder.get(i)).setCaptainBoost(i+1);
        }
    }

    private void giveAWinTokenToTheSeasonWinner(Map<String,Player> players, List<String> teamColoursInCurrentOrder){

        players.get(teamColoursInCurrentOrder.get(0)).addWinToThePlayer();
    }
}

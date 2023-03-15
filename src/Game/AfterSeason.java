package Game;

import Player.Player;

import java.sql.*;
import java.util.List;
import java.util.Map;

import static Game.FinalVariables.FIRST_PLACE_IN_THE_SEASON_INCOME;
import static Game.FinalVariables.STEP_BEHIND_THE_FIRST_PLACE_FOR_SEASON_INCOME;
import static Game.Game.*;
import static Season.FootballMatches.matchPlayerVsFootballCupTeam;

public class AfterSeason {
    public static boolean afterSeason() throws SQLException, ClassNotFoundException, InterruptedException {
        setTeamColoursInOrderAfterTheSeason(teamColoursInCurrentOrder);
        setTheBudgetOfEveryPlayerAfterTheSeason(players,teamColoursInCurrentOrder);
        setCaptainBoostsAfterTheSeason(players,teamColoursInCurrentOrder);
        giveAWinTokenToTheSeasonWinner(players,teamColoursInCurrentOrder);

        if(checkIfWeHaveWinner()){
            printWinningMessage();
            return false;
        }

        if (checkIfWeHavePotentialWinner()){
            if(choiceForUserToPlayTheFootballCup() == 1){
                return playFootballCupMatch();
            }
        }

        return true;
    }
    private static void setTheBudgetOfEveryPlayerAfterTheSeason(Map<String, Player> players, List<String> teamColoursInCurrentOrder){
        for (int steps = 0; steps < players.size(); steps++) {
            Player currentPlayer = players.get(teamColoursInCurrentOrder.get(steps));
            currentPlayer.addMoneyToThePlayer(FIRST_PLACE_IN_THE_SEASON_INCOME - steps * STEP_BEHIND_THE_FIRST_PLACE_FOR_SEASON_INCOME);
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

    private static boolean checkIfWeHaveWinner(){
        return players.get(teamColoursInCurrentOrder.get(0)).getCurrentPointsInTheSeason() >= 100;
    }

    private static void printWinningMessage(){
        System.out.println("WE HAVE A CHAMPION");
        System.out.println("CONGRATULATIONS " + players.get(teamColoursInCurrentOrder.get(0)) + " TEAM, YOU WON THE TOURNAMENT");
    }

    private static boolean checkIfWeHavePotentialWinner(){
        return players.get(teamColoursInCurrentOrder.get(0)).getSeasonWins() >= 3;
    }

    private static int choiceForUserToPlayTheFootballCup(){
        printMessageForPlayerThatCanPlayFootballCup();
        return choiceMadeByTheUserValidation(0,2);
    }

    private static void printMessageForPlayerThatCanPlayFootballCup(){
        System.out.println(players.get(teamColoursInCurrentOrder.get(0)).getPlayerColour() + ", you are allowed to play the FootballClub vs one random team, because you already have 3 wins.\n" +
                "If you win the match, you are the FootballClub champion\n" +
                "If not, you are losing all your current wins\n" +
                "Are you going to play the match or we can continue to the next season: \n" +
                "1. Play the match\n" +
                "2. Continue to season");
    }

    private static boolean playFootballCupMatch() throws SQLException, ClassNotFoundException, InterruptedException {
        if(matchPlayerVsFootballCupTeam(players.get(teamColoursInCurrentOrder.get(0)), getRandomFootballCupTeamFromDB())){
            System.out.println();
            printWinningMessage();
            return true;
        }
        return false;
    }

    private static FootballCupTeam getRandomFootballCupTeamFromDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/FootballGamePlayers";
        String username = "postgres";
        String password = "password";

        Connection connection = DriverManager.getConnection(url,username,password);
        String query = "SELECT * FROM public.\"FootballCupTeams\" ORDER BY RANDOM() LIMIT 1;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return new FootballCupTeam(resultSet.getString(2),
                                    resultSet.getInt(3),
                                    resultSet.getInt(4),
                                    resultSet.getInt(5));
    }
}

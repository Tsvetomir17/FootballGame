package Game.AfterSeason;

import FootballCupTeam.FootballCupTeam;
import java.sql.*;
import Game.InputValidator;
import Game.Season.FootballMatches;
import Player.Player;

public class PotentialWinning {
    public boolean checkIfWeHaveWinner(Player player){

        return player.getCurrentPointsInTheSeason() >= 100;
    }

    public void printWinningMessage(Player player){

        System.out.println("WE HAVE A CHAMPION");
        System.out.println("CONGRATULATIONS " + player.getPlayerColour() + " TEAM, YOU WON THE TOURNAMENT");
    }

    public boolean checkIfWeHavePotentialWinner(Player player){

        return player.getSeasonWins() >= 3;
    }

    public int choiceForUserToPlayTheFootballCup(Player player){

        printMessageForPlayerThatCanPlayFootballCup(player);
        return InputValidator.choiceMadeByTheUserValidation(1,2);
    }

    private static void printMessageForPlayerThatCanPlayFootballCup(Player player){

        System.out.println(player.getPlayerColour() + ", you are allowed to play the FootballClub vs one random team, because you already have 3 wins.\n" +
                "If you win the match, you are the FootballClub champion\n" +
                "If not, you are losing all your current wins\n" +
                "Are you going to play the match or we can continue to the next season: \n" +
                "1. Play the match\n" +
                "2. Continue to season");
    }

    public boolean playFootballCupMatch(Player player) throws SQLException, ClassNotFoundException, InterruptedException {

        FootballCupTeam footballCupTeam = getRandomFootballCupTeamFromDB();
        footballCupTeam.printTeam();

        FootballMatches footballMatches = new FootballMatches();
        if(footballMatches.matchPlayerVsFootballCupTeam(player, footballCupTeam)){

            System.out.println();
            printWinningMessage(player);
            return true;
        }
        return false;
    }

    private FootballCupTeam getRandomFootballCupTeamFromDB() throws ClassNotFoundException, SQLException {

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

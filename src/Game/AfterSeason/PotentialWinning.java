package Game.AfterSeason;

import FootballCupTeam.FootballCupTeam;

import java.sql.*;

import static Game.Game.getPlayers;
import static Game.Game.choiceMadeByTheUserValidation;
import static Game.Game.getTeamColoursInCurrentOrder;
import static Game.Season.FootballMatches.matchPlayerVsFootballCupTeam;

public class PotentialWinning {
    static boolean checkIfWeHaveWinner(){

        return getPlayers().get(getTeamColoursInCurrentOrder().get(0)).getCurrentPointsInTheSeason() >= 100;
    }

    static void printWinningMessage(){

        System.out.println("WE HAVE A CHAMPION");
        System.out.println("CONGRATULATIONS " + getPlayers().get(getTeamColoursInCurrentOrder().get(0)) + " TEAM, YOU WON THE TOURNAMENT");
    }

    static boolean checkIfWeHavePotentialWinner(){

        return getPlayers().get(getTeamColoursInCurrentOrder().get(0)).getSeasonWins() >= 3;
    }

    static int choiceForUserToPlayTheFootballCup(){

        printMessageForPlayerThatCanPlayFootballCup();
        return choiceMadeByTheUserValidation(0,2);
    }

    private static void printMessageForPlayerThatCanPlayFootballCup(){

        System.out.println(getPlayers().get(getTeamColoursInCurrentOrder().get(0)).getPlayerColour() + ", you are allowed to play the FootballClub vs one random team, because you already have 3 wins.\n" +
                "If you win the match, you are the FootballClub champion\n" +
                "If not, you are losing all your current wins\n" +
                "Are you going to play the match or we can continue to the next season: \n" +
                "1. Play the match\n" +
                "2. Continue to season");
    }

    static boolean playFootballCupMatch() throws SQLException, ClassNotFoundException, InterruptedException {

        FootballCupTeam footballCupTeam = getRandomFootballCupTeamFromDB();
        footballCupTeam.printTeam();
        if(matchPlayerVsFootballCupTeam(getPlayers().get(getTeamColoursInCurrentOrder().get(0)), footballCupTeam)){

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

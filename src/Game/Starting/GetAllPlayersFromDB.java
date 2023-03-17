package Game.Starting;

import FootballPlayer.FootballPlayer;

import java.sql.*;

import static Game.Game.pushPlayerToTheDeck;

public class GetAllPlayersFromDB {
    public static void fillTheListOfFootballPlayers() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/FootballGamePlayers";
        String username = "postgres";
        String password = "password";

        Connection connection = DriverManager.getConnection(url,username,password);
        String query = "SELECT * FROM \"FootballPlayers\" ORDER BY RANDOM()";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){

            FootballPlayer footballPlayerToPushInTheDeck = new FootballPlayer(
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getBoolean(6),
                    resultSet.getBoolean(7),
                    resultSet.getBoolean(8),
                    resultSet.getInt(9),
                    resultSet.getInt(10)
            );
            pushPlayerToTheDeck(footballPlayerToPushInTheDeck);
        }
    }
}

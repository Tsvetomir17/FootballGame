import Game.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/FootballGamePlayers";
        String username = "postgres";
        String password = "password";
        Connection connection = DriverManager.getConnection(url,username,password);
        String query = "SELECT * FROM \"FootballPlayers\"";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<String> name = new ArrayList<>();
        while(resultSet.next()){
            name.add(resultSet.getString(2));
        }
        System.out.println(name);
        Game game = Game.getGameInstance();
        game.startGame();
    }
}
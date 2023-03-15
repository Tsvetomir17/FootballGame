import Game.Game;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {

        Game game = Game.getGameInstance();
        game.startGame();
    }
}
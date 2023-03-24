package Game.Starting;

import Game.Game;

import java.sql.SQLException;

public interface StartingAction {

    void action(Game game) throws ClassNotFoundException, SQLException;
}

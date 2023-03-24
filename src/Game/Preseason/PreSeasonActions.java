package Game.Preseason;

import Game.Game;
import java.sql.SQLException;

public interface PreSeasonActions {

    void action(Game game) throws SQLException, ClassNotFoundException, InterruptedException;

}

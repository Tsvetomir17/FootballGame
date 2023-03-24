package Game.AfterSeason;

import Game.Game;
import java.sql.SQLException;

public interface AfterSeasonAction {

    boolean action(Game game) throws SQLException, ClassNotFoundException, InterruptedException;
}

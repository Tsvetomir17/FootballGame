package Game;

import FootballPlayer.FootballPlayer;
import Player.Player;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static Game.Game.*;
import static java.lang.Math.min;

public class PlayerDevelopment {

    public static void upgradeFootballPlayers() throws SQLException, ClassNotFoundException {
        for (int i = 0; i < players.size(); i++) {

            Player currentPlayer = players.get(teamColoursInCurrentOrder.get(i));
            Set<String> alreadyTriedToUpgradePlayers = new HashSet<>();

            int maximumStarsToGiveToAPlayer = currentPlayer.getPlayerUpgradeBoard().getTrainingCentreLevel();
            int playersToUpgrade = min(maximumStarsToGiveToAPlayer, maximumPlayersThatCanBeUpgraded(currentPlayer));

            printStartingMessageForUpgrading(currentPlayer, maximumStarsToGiveToAPlayer, playersToUpgrade);

            for (int j = 0; j < playersToUpgrade; j++) {

                System.out.println("Choose a player to upgrade: ");

                int choiceByPlayer = choiceByUser(alreadyTriedToUpgradePlayers,currentPlayer);
                if(choiceByPlayer == -1) break;

                FootballPlayer currentFootballPlayer = currentPlayer.getFullTeam().getFootballPlayerAtIndex(choiceByPlayer);
                if(currentFootballPlayer.getCurrentFootballPlayerRating() == currentFootballPlayer.getMaxFootballPlayerRating()){
                    System.out.println("This player reached his maximum potential, try again with another one");
                    j--;
                    continue;
                }

                currentPlayer.upgradeFootballPlayer(rollTheDiceForThePlayerAndReturnTheNewOneIfTheDiceIsGood(
                        currentFootballPlayer,maximumStarsToGiveToAPlayer), choiceByPlayer);
                alreadyTriedToUpgradePlayers.add(currentFootballPlayer.getFootballPlayerName());
            }
        }
    }

    private static void printStartingMessageForUpgrading(Player currentPlayer, int maximumStarsToGiveToAPlayer, int playersToUpgrade){
        System.out.println(currentPlayer.getPlayerColour() + ", it is your turn to upgrade your players.");
        System.out.println("You can choose a player to be upgraded or press '0' to skip this phase");
        System.out.println("0. Skip");
        currentPlayer.printFullTeam();
        System.out.println(currentPlayer.getPlayerColour() + ", it is your time to upgrade!");
        System.out.println("You can add max " + maximumStarsToGiveToAPlayer + " stars" +
                " to " + playersToUpgrade + " players.");
    }

    private static boolean isThePlayerUpgradedThisSeason(Set<String> alreadyTriedToUpgradePlayers, FootballPlayer player){
        return alreadyTriedToUpgradePlayers.contains(player.getFootballPlayerName());
    }

    private static int choiceByUser(Set<String> alreadyTriedToUpgradePlayers, Player currentPlayer){
        int choiceByPlayer = choiceMadeByTheUserValidation(-1,currentPlayer.getFullTeam().getFullTeamSize()) -1;
        if(choiceByPlayer == -1) return  choiceByPlayer;
        while(isThePlayerUpgradedThisSeason
                (alreadyTriedToUpgradePlayers, currentPlayer.getFullTeam().getFootballPlayerAtIndex(choiceByPlayer))){
            System.out.println("You already trained that player this season, try another one");
            choiceByPlayer = choiceMadeByTheUserValidation(-1,currentPlayer.getFullTeam().getFullTeamSize()) -1;
            if(choiceByPlayer == -1) return  choiceByPlayer;
        }

        return choiceByPlayer;
    }
    private static int maximumPlayersThatCanBeUpgraded(Player player){
        int counter = 0;
        for (int i = 0; i < player.getFullTeam().getFullTeamSize(); i++) {
            if(player.getFullTeam().getFootballPlayerAtIndex(i).getMaxFootballPlayerRating() ==
                player.getFullTeam().getFootballPlayerAtIndex(i).getCurrentFootballPlayerRating())
            {
                counter++;
            }
        }
        return counter;
    }

    private static FootballPlayer rollTheDiceForThePlayerAndReturnTheNewOneIfTheDiceIsGood
            (FootballPlayer footballPlayer,int maximumStarsToGive) throws SQLException, ClassNotFoundException {
        int rolledNumber = Dice.getInstance().rollDice();
        System.out.println("You rolled " + rolledNumber + '!');

        if(rolledNumber > footballPlayer.getCurrentFootballPlayerRating()){
            int upgradeVariable = min(maximumStarsToGive + footballPlayer.getCurrentFootballPlayerRating(),
                                    min(rolledNumber, footballPlayer.getMaxFootballPlayerRating()));

            footballPlayer = getThePlayerFromTheDatabaseOfDevelopedPlayers(footballPlayer.getFootballPlayerName(), upgradeVariable);
            footballPlayer.printFootballPlayerAsCard();
        }else{
            System.out.println("Good luck next time!");
        }

        return footballPlayer;
    }

    private static FootballPlayer getThePlayerFromTheDatabaseOfDevelopedPlayers(String footballPlayerName,
                                                                                int footballPlayerRating)
            throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/FootballGamePlayers";
        String username = "postgres";
        String password = "password";

        Connection connection = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM \"DevelopedFootballPlayers\" WHERE name = '" + footballPlayerName +
                "' AND current_rating = " + footballPlayerRating;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return new FootballPlayer(
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
    }
}

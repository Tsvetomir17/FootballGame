package Player;

import FootballPlayer.FootballPlayer;

public class Player {
    private final PlayerTeamColours teamColour;
    private final UpgradeBoard playerUpgradeBoard;
    private final FullTeam fullTeam;
    private int playerMoney;
    private int currentPlayerOverall;

    public Player(String colour){
        teamColour= playerSetTeamColour(colour);
        playerUpgradeBoard = new UpgradeBoard();
        fullTeam = new FullTeam();
        currentPlayerOverall = 0;
        playerMoney = 0;
    }

    private PlayerTeamColours playerSetTeamColour(String colour){
        if(colour.equalsIgnoreCase("RED")) return PlayerTeamColours.RED;
        else if(colour.equalsIgnoreCase("BLUE")) return PlayerTeamColours.BLUE;
        else if(colour.equalsIgnoreCase("GREEN")) return PlayerTeamColours.GREEN;
        else if(colour.equalsIgnoreCase("PURPLE")) return PlayerTeamColours.PURPLE;
        else if(colour.equalsIgnoreCase("YELLOW")) return PlayerTeamColours.YELLOW;
        return PlayerTeamColours.PINK;
    }
    public void addMoneyToThePlayer(int money){
        playerMoney += money;
    }

    public void spendMoney(int money){
        if(money > playerMoney){
            throw new IllegalArgumentException("You can not spend more money than you have");
        }
        playerMoney -= money;
    }

    public int getPlayerMoney(){
        return playerMoney;
    }
    public PlayerTeamColours getPlayerColour(){
        return teamColour;
    }

    public int getCurrentPlayerOverall(){
        return currentPlayerOverall;
    }

    public void addFootballPlayerToTheTeam(FootballPlayer player){
        fullTeam.addPlayerToTheTeam(player);
        currentPlayerOverall += player.getCurrentFootballPlayerRating();
    }

    public void printFullTeam(){
        fullTeam.printFullTeam();
    }

    public UpgradeBoard getPlayerUpgradeBoard(){
        return playerUpgradeBoard;
    }

    public FullTeam getFullTeam(){
        return fullTeam;
    }
}

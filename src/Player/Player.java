package Player;

public class Player {
    private PlayerTeamColours teamColour;
    private UpgradeBoard playerUpgradeBoard;
    private FullTeamAndStaff fullTeamAndStaff;
    private int playerMoney;
    private int currentPlayerOverall;

    public Player(String colour){
        teamColour= playerSetTeamColour(colour);
        playerUpgradeBoard = new UpgradeBoard();
        fullTeamAndStaff = new FullTeamAndStaff();
        currentPlayerOverall = 0;
    }

    private PlayerTeamColours playerSetTeamColour(String colour){
        if(colour.equalsIgnoreCase("RED")) return PlayerTeamColours.RED;
        else if(colour.equalsIgnoreCase("BLUE")) return PlayerTeamColours.BLUE;
        else if(colour.equalsIgnoreCase("GREEN")) return PlayerTeamColours.GREEN;
        else if(colour.equalsIgnoreCase("PURPLE")) return PlayerTeamColours.PURPLE;
        else if(colour.equalsIgnoreCase("YELLOW")) return PlayerTeamColours.YELLOW;
        return PlayerTeamColours.PINK;
    }
    public void setPlayerMoney(int money){
        playerMoney = money;
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
}

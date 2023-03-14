package Player;

import FootballPlayer.FootballPlayer;

public class Player {
    private final PlayerTeamColours teamColour;
    private final UpgradeBoard playerUpgradeBoard;
    private final FullTeam fullTeam;
    private int playerMoney;
    private int currentPlayerOverall;

    private double captainBoost;

    public Player(String colour){
        teamColour= playerSetTeamColour(colour);
        playerUpgradeBoard = new UpgradeBoard();
        fullTeam = new FullTeam();
        currentPlayerOverall = 0;
        playerMoney = 0;
        captainBoost = 1;
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

    private void purchaseFootballPlayer(FootballPlayer player, int price){
        fullTeam.addPlayerToTheTeam(player);
        currentPlayerOverall += player.getCurrentFootballPlayerRating();
        playerMoney -= price;
    }

    public void addFootballPlayerToTheTeamViaDraft(FootballPlayer player){
        purchaseFootballPlayer(player, 0);
    }

    public void addFootballPlayerToTheTeamViaScouting(FootballPlayer player){
        purchaseFootballPlayer(player, player.getScoutingPrice());
    }

    public void addFootballPlayerToTheTeamViaDeadlineDay(FootballPlayer player, int price){
        purchaseFootballPlayer(player,price);
    }

    public void discardFootballPlayerFromTheTeam(int indexOfThePlayer){
        currentPlayerOverall -= fullTeam.getFootballPlayerAtIndex(indexOfThePlayer).getCurrentFootballPlayerRating();
        playerMoney += fullTeam.getFootballPlayerAtIndex(indexOfThePlayer).getScoutingPrice();
        fullTeam.removePlayerAtIndex(indexOfThePlayer);
    }

    public void upgradeFootballPlayer(FootballPlayer footballPlayer, int index){
        currentPlayerOverall = currentPlayerOverall + footballPlayer.getCurrentFootballPlayerRating() -
                fullTeam.getFootballPlayerAtIndex(index).getCurrentFootballPlayerRating();
        fullTeam.upgradePlayer(footballPlayer,index);
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

    public double getCaptainBoost() {
        return captainBoost;
    }

    public void setCaptainBoost(double captainBoost) {
        if(captainBoost < 0){
            throw new IllegalArgumentException();
        }
        this.captainBoost = captainBoost;
    }
}

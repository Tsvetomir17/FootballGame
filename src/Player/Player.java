package Player;

import FootballPlayer.FootballPlayer;

public class Player {
    private final PlayerTeamColours teamColour;
    private final UpgradeBoard upgradeBoard;
    private final FullTeam fullTeam;
    private int money;
    private int currentOverall;
    private int currentPointsInTheSeason;
    private double captainBoost;
    private int seasonWins;

    public Player(String colour){

        teamColour= playerSetTeamColour(colour);
        upgradeBoard = new UpgradeBoard();
        fullTeam = new FullTeam();
        currentOverall = 0;
        money = 0;
        captainBoost = 1;
        seasonWins = 0;
    }

    public void addMoneyToThePlayer(int money){

        this.money += money;
    }

    public void spendMoney(int money){

        if(money > this.money){

            throw new IllegalArgumentException("You can not spend more money than you have");
        }
        this.money -= money;
    }

    public int getPlayerMoney(){

        return money;
    }
    public PlayerTeamColours getPlayerColour(){

        return teamColour;
    }

    public int getCurrentOverall(){

        return currentOverall;
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

        currentOverall -= fullTeam.getFootballPlayerAtIndex(indexOfThePlayer).getCurrentFootballPlayerRating();
        money += fullTeam.getFootballPlayerAtIndex(indexOfThePlayer).getScoutingPrice();
        fullTeam.removePlayerAtIndex(indexOfThePlayer);
    }

    public void upgradeFootballPlayer(FootballPlayer footballPlayer, int index){

        currentOverall = currentOverall + footballPlayer.getCurrentFootballPlayerRating() -
                fullTeam.getFootballPlayerAtIndex(index).getCurrentFootballPlayerRating();
        fullTeam.upgradePlayer(footballPlayer,index);
    }
    public void printFullTeam(){

        fullTeam.printFullTeam();
    }

    public UpgradeBoard getPlayerUpgradeBoard(){

        return upgradeBoard;
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

    public int getCurrentPointsInTheSeason() {

        return currentPointsInTheSeason;
    }

    public void setCurrentPointsInTheSeason(int currentPointsInTheSeason) {

        this.currentPointsInTheSeason = currentPointsInTheSeason;
    }

    public void addPointsForTheCurrentSeason(int pointsToAdd){

        this.currentPointsInTheSeason += pointsToAdd;
    }

    public void addWinToThePlayer(){

        seasonWins++;
    }

    public int getSeasonWins() {

        return seasonWins;
    }

    private void purchaseFootballPlayer(FootballPlayer player, int price){

        fullTeam.addPlayerToTheTeam(player);
        currentOverall += player.getCurrentFootballPlayerRating();
        money -= price;
    }

    private PlayerTeamColours playerSetTeamColour(String colour){

        if(colour.equalsIgnoreCase("RED")) return PlayerTeamColours.RED;
        else if(colour.equalsIgnoreCase("BLUE")) return PlayerTeamColours.BLUE;
        else if(colour.equalsIgnoreCase("GREEN")) return PlayerTeamColours.GREEN;
        else if(colour.equalsIgnoreCase("PURPLE")) return PlayerTeamColours.PURPLE;
        else if(colour.equalsIgnoreCase("YELLOW")) return PlayerTeamColours.YELLOW;
        return PlayerTeamColours.PINK;
    }
}

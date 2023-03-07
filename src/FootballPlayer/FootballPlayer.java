package FootballPlayer;

public class FootballPlayer {
    private String footballPlayerName;
    private int currentFootballPlayerRating;
    private int maxFootballPlayerRating;
    private FootballPlayersPositions footballPlayerPosition;
    private boolean hasLeftChemistryStyle;
    private boolean hasRightChemistryStyle;
    private boolean isCaptain;
    private int scoutingPrice;
    private int deadlineDayPrice;

    public FootballPlayer(String footballPlayerName, int currentFootballPlayerRating, int maxFootballPlayerRating,
                          FootballPlayersPositions footballPlayerPosition, boolean hasLeftChemistryStyle,
                          boolean hasRightChemistryStyle, boolean isCaptain, int scoutingPrice,
                          int deadlineDayPrice) {
        this.footballPlayerName = footballPlayerName;
        this.currentFootballPlayerRating = currentFootballPlayerRating;
        this.maxFootballPlayerRating = maxFootballPlayerRating;
        this.footballPlayerPosition = footballPlayerPosition;
        this.hasLeftChemistryStyle = hasLeftChemistryStyle;
        this.hasRightChemistryStyle = hasRightChemistryStyle;
        this.isCaptain = isCaptain;
        this.scoutingPrice = scoutingPrice;
        this.deadlineDayPrice = deadlineDayPrice;
    }

    public String getFootballPlayerName() {
        return footballPlayerName;
    }

    public int getCurrentFootballPlayerRating() {
        return currentFootballPlayerRating;
    }

    public int getMaxFootballPlayerRating() {
        return maxFootballPlayerRating;
    }

    public FootballPlayersPositions getFootballPlayerPosition() {
        return footballPlayerPosition;
    }

    public boolean isHasLeftChemistryStyle() {
        return hasLeftChemistryStyle;
    }

    public boolean isHasRightChemistryStyle() {
        return hasRightChemistryStyle;
    }

    public boolean isCaptain() {
        return isCaptain;
    }

    public int getScoutingPrice() {
        return scoutingPrice;
    }

    public int getDeadlineDayPrice() {
        return deadlineDayPrice;
    }

    public void upgradePlayerRatingWith(int upgradeAmount){
        currentFootballPlayerRating += upgradeAmount;

        if(currentFootballPlayerRating > maxFootballPlayerRating){
            currentFootballPlayerRating = maxFootballPlayerRating;
        }
    }

    public void giveLeftChemistryStyle(){
        hasLeftChemistryStyle = true;
    }

    public void giveRightChemistryStyle(){
        hasRightChemistryStyle = true;
    }

    public void giveCaptainArmband(){
        isCaptain = true;
    }
}

package FootballPlayer;

import static GameModes.FinalVariables.*;

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
                          String footballPlayerPosition, boolean hasLeftChemistryStyle,
                          boolean hasRightChemistryStyle, boolean isCaptain, int scoutingPrice,
                          int deadlineDayPrice) {
        this.footballPlayerName = footballPlayerName;
        this.currentFootballPlayerRating = currentFootballPlayerRating;
        this.maxFootballPlayerRating = maxFootballPlayerRating;
        this.footballPlayerPosition = setFootballPlayerPosition(footballPlayerPosition);
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

    public String getFootballPlayerPositionAsString() {

        if(footballPlayerPosition.equals(FootballPlayersPositions.GOALKEEPER)) return "GOALKEEPER";
        else if(footballPlayerPosition.equals(FootballPlayersPositions.DEFENDER)) return "DEFENDER";
        else if(footballPlayerPosition.equals(FootballPlayersPositions.MIDFIELDER)) return "MIDFIELDER";
        else if(footballPlayerPosition.equals(FootballPlayersPositions.ATTACKER)) return "ATTACKER";
        else return "PURPLE";
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

    private FootballPlayersPositions setFootballPlayerPosition(String position){
        if(position.equalsIgnoreCase("GOALKEEPER")) return FootballPlayersPositions.GOALKEEPER;
        else if(position.equalsIgnoreCase("DEFENDER")) return FootballPlayersPositions.DEFENDER;
        else if(position.equalsIgnoreCase("MIDFIELDER")) return FootballPlayersPositions.MIDFIELDER;
        else if(position.equalsIgnoreCase("ATTACKER")) return FootballPlayersPositions.ATTACKER;
        else return FootballPlayersPositions.PURPLE;
    }

    public void printFootballPlayer(){
        PrintFunctionsForFootballPlayers.printFootballPlayerOnOneRow(this);
    }

    public void printFootballPlayerAsCard(){
        PrintFunctionsForFootballPlayers.printFootballPlayerAsCard(this);
    }
}

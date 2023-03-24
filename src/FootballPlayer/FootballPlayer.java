package FootballPlayer;

public class FootballPlayer {
    private final String name;
    private final int currentRating;
    private final int maxRating;
    private final FootballPlayersPositions position;
    private final boolean hasLeftChemistryStyle;
    private final boolean hasRightChemistryStyle;
    private final boolean isCaptain;
    private final int scoutingPrice;
    private final int deadlineDayPrice;

    private final PrintFunctionsForFootballPlayers printFunctionsForFootballPlayers = new PrintFunctionsForFootballPlayers();

    public FootballPlayer(String footballPlayerName, int currentFootballPlayerRating, int maxFootballPlayerRating,
                          String footballPlayerPosition, boolean hasLeftChemistryStyle,
                          boolean hasRightChemistryStyle, boolean isCaptain, int scoutingPrice,
                          int deadlineDayPrice) {

        this.name = footballPlayerName;
        this.currentRating = currentFootballPlayerRating;
        this.maxRating = maxFootballPlayerRating;
        this.position = setFootballPlayerPosition(footballPlayerPosition);
        this.hasLeftChemistryStyle = hasLeftChemistryStyle;
        this.hasRightChemistryStyle = hasRightChemistryStyle;
        this.isCaptain = isCaptain;
        this.scoutingPrice = scoutingPrice;
        this.deadlineDayPrice = deadlineDayPrice;
    }

    public String getFootballPlayerName() {

        return name;
    }

    public int getCurrentFootballPlayerRating() {

        return currentRating;
    }

    public int getMaxFootballPlayerRating() {

        return maxRating;
    }

    public String getFootballPlayerPositionAsString() {

        if(position.equals(FootballPlayersPositions.GOALKEEPER)) return "GOALKEEPER";
        else if(position.equals(FootballPlayersPositions.DEFENDER)) return "DEFENDER";
        else if(position.equals(FootballPlayersPositions.MIDFIELDER)) return "MIDFIELDER";
        else if(position.equals(FootballPlayersPositions.ATTACKER)) return "ATTACKER";
        else return "PURPLE";
    }

    public FootballPlayersPositions getFootballPlayerPosition(){

        return position;
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

    public void printFootballPlayer(){

        printFunctionsForFootballPlayers.printFootballPlayerOnOneRow(this);
    }

    public void printFootballPlayerAsCard(){

        printFunctionsForFootballPlayers.printFootballPlayerAsCard(this);
    }

    private FootballPlayersPositions setFootballPlayerPosition(String position){

        if(position.equalsIgnoreCase("GOALKEEPER")) return FootballPlayersPositions.GOALKEEPER;
        else if(position.equalsIgnoreCase("DEFENDER")) return FootballPlayersPositions.DEFENDER;
        else if(position.equalsIgnoreCase("MIDFIELDER")) return FootballPlayersPositions.MIDFIELDER;
        else if(position.equalsIgnoreCase("ATTACKER")) return FootballPlayersPositions.ATTACKER;
        else return FootballPlayersPositions.PURPLE;
    }
}

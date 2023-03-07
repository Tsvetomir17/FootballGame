package FootballPlayer;

import static GameModes.FinalVariables.*;

public class PrintFunctionsForFootballPlayers {
    public static void printFootballPlayerAsCard(FootballPlayer player){

        printRowOneForCard(player);
        printRowTwoForCard(player);
        printRowThreeForCard(player);
        printRowFourForCard(player);
        printRowFiveForCard(player);
        System.out.println();
    }

    public static void printFootballPlayerOnOneRow(FootballPlayer player){
        System.out.print(player.getFootballPlayerName() + " ");
        System.out.print(getStringWithTheStarsOfThePlayer(player) + " ");
        System.out.print(player.getFootballPlayerPositionAsString() + " ");
        System.out.print(getStringWithChemistryStylesAndCaptain(player) + " ");
        System.out.println(player.getScoutingPrice() + "M.  " + player.getDeadlineDayPrice() + "M.");
    }

    private static void printCentredTheCurrentRow(String currentRow){
        int leftShift = (FOOTBALL_PLAYER_CARD_MAXIMUM_ROW_SIZE - currentRow.length())/2;
        int rightShift = (FOOTBALL_PLAYER_CARD_MAXIMUM_ROW_SIZE - currentRow.length()) - leftShift;
        System.out.printf("%" + leftShift + "s%s%" + rightShift + "s%n", "", currentRow, "");
    }

    private static void printRowOneForCard(FootballPlayer player){
        printCentredTheCurrentRow(player.getFootballPlayerName());
    }

    private static String getStringWithTheStarsOfThePlayer(FootballPlayer player){
        String currentFootballPlayerStars = "";
        int currentFootballPlayerRating = player.getCurrentFootballPlayerRating();
        int emptyStarsToAdd = player.getMaxFootballPlayerRating() - currentFootballPlayerRating;

        for (int i = 0; i < currentFootballPlayerRating; i++) {
            currentFootballPlayerStars += FULL_STAR_FOR_FOOTBALL_PLAYERS;
        }
        for (int i = 0; i < emptyStarsToAdd; i++) {
            currentFootballPlayerStars += EMPTY_STAR_FOR_FOOTBALL_PLAYERS;
        }
        return currentFootballPlayerStars;
    }

    private static void printRowTwoForCard(FootballPlayer player){
        printCentredTheCurrentRow(getStringWithTheStarsOfThePlayer(player));
    }
    private static void printRowThreeForCard(FootballPlayer player){
        printCentredTheCurrentRow(player.getFootballPlayerPositionAsString());
    }

    private static String getStringWithChemistryStylesAndCaptain(FootballPlayer player){
        String result = "";
        String fourEmptySpaces = "    ";
        if(player.isHasLeftChemistryStyle()){
            result += CHECK_MARK_POINTING_IF_FOOTBALL_PLAYER_HAS_CHEMISTRY_STYLE_FROM_THAT_SIDE;
        }else{
            result += ' ';
        }
        result += fourEmptySpaces;
        if(player.isCaptain()){
            result += CAPTAIN_SYMBOL_FOR_FOOTBALL_PLAYERS;
        }else{
            result += ' ';
        }
        result += fourEmptySpaces;
        if(player.isHasRightChemistryStyle()){
            result += CHECK_MARK_POINTING_IF_FOOTBALL_PLAYER_HAS_CHEMISTRY_STYLE_FROM_THAT_SIDE;
        }else{
            result += ' ';
        }
        return result;
    }
    private static void printRowFourForCard(FootballPlayer player){
        System.out.println(getStringWithChemistryStylesAndCaptain(player));
    }

    private static void printRowFiveForCard(FootballPlayer player){
        String addOneMoreSpaceInTheBeginningIfPriceIsUnderTen = " ";
        if(player.getScoutingPrice() < 10){
            addOneMoreSpaceInTheBeginningIfPriceIsUnderTen += " ";
        }
            System.out.println(addOneMoreSpaceInTheBeginningIfPriceIsUnderTen + player.getScoutingPrice() + "M."
            + "   " + player.getDeadlineDayPrice() + "M.");
    }

}

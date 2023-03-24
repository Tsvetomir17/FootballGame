package FootballPlayer;

import static Game.FinalVariables.FOOTBALL_PLAYER_CARD_MAXIMUM_ROW_SIZE;
import static Game.FinalVariables.FULL_STAR_FOR_FOOTBALL_PLAYERS;
import static Game.FinalVariables.EMPTY_STAR_FOR_FOOTBALL_PLAYERS;
import static Game.FinalVariables.CHECK_MARK_POINTING_IF_FOOTBALL_PLAYER_HAS_CHEMISTRY_STYLE_FROM_THAT_SIDE;
import static Game.FinalVariables.CAPTAIN_SYMBOL_FOR_FOOTBALL_PLAYERS;

public class PrintFunctionsForFootballPlayers implements IPrintFunctionsForFootballPlayers {

    @Override
    public void printFootballPlayerAsCard(FootballPlayer player){

        printRowOneForCard(player);
        printRowTwoForCard(player);
        printRowThreeForCard(player);
        printRowFourForCard(player);
        printRowFiveForCard(player);
        System.out.println();
    }

    @Override
    public void printFootballPlayerOnOneRow(FootballPlayer player){

        System.out.print(player.getFootballPlayerName() + " ");
        System.out.print(getStringWithTheStarsOfThePlayer(player) + " ");
        System.out.print(player.getFootballPlayerPositionAsString() + " ");
        System.out.print(getStringWithChemistryStylesAndCaptain(player) + " ");
        System.out.println(player.getScoutingPrice() + "M.  " + player.getDeadlineDayPrice() + "M.");
    }

    private void printCentredTheCurrentRow(String currentRow){

        int leftShift = (FOOTBALL_PLAYER_CARD_MAXIMUM_ROW_SIZE - currentRow.length())/2;
        int rightShift = (FOOTBALL_PLAYER_CARD_MAXIMUM_ROW_SIZE - currentRow.length()) - leftShift;
        System.out.printf("%" + leftShift + "s%s%" + rightShift + "s%n", "", currentRow, "");
    }

    private void printRowOneForCard(FootballPlayer player){

        printCentredTheCurrentRow(player.getFootballPlayerName());
    }

    private String getStringWithTheStarsOfThePlayer(FootballPlayer player){

        StringBuilder currentFootballPlayerStars = new StringBuilder();
        int currentFootballPlayerRating = player.getCurrentFootballPlayerRating();
        int emptyStarsToAdd = player.getMaxFootballPlayerRating() - currentFootballPlayerRating;

        currentFootballPlayerStars.append(String.valueOf(FULL_STAR_FOR_FOOTBALL_PLAYERS).repeat(Math.max(0, currentFootballPlayerRating)));
        currentFootballPlayerStars.append(String.valueOf(EMPTY_STAR_FOR_FOOTBALL_PLAYERS).repeat(Math.max(0, emptyStarsToAdd)));
        return currentFootballPlayerStars.toString();
    }

    private void printRowTwoForCard(FootballPlayer player){

        printCentredTheCurrentRow(getStringWithTheStarsOfThePlayer(player));
    }
    private void printRowThreeForCard(FootballPlayer player){

        printCentredTheCurrentRow(player.getFootballPlayerPositionAsString());
    }

    private String getStringWithChemistryStylesAndCaptain(FootballPlayer player){

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
    private void printRowFourForCard(FootballPlayer player){

        System.out.println(getStringWithChemistryStylesAndCaptain(player));
    }

    private void printRowFiveForCard(FootballPlayer player){

        String addOneMoreSpaceInTheBeginningIfPriceIsUnderTen = " ";
        if(player.getScoutingPrice() < 10){

            addOneMoreSpaceInTheBeginningIfPriceIsUnderTen += " ";
        }

        System.out.println(addOneMoreSpaceInTheBeginningIfPriceIsUnderTen + player.getScoutingPrice() + "M."
        + "   " + player.getDeadlineDayPrice() + "M.");
    }
}

package Game.Preseason;

import Player.Player;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static Game.FinalVariables.SCOUTING;
import static Game.FinalVariables.YOUTH_DEVELOPMENT;
import static Game.FinalVariables.STADIUM_INCOME;
import static Game.FinalVariables.UPGRADES_PER_PLAYER_PER_SEASON;
import static Game.FinalVariables.MAX_LEVEL_UPGRADE;
import static Game.Game.getPlayers;
import static Game.Game.choiceMadeByTheUserValidation;
import static Game.Game.getPlayersInTheGameSize;
import static Game.Game.getTeamColoursInCurrentOrder;

public class Upgrades {

    public static void upgrades() throws InterruptedException {

        for (int i = 0; i < getPlayersInTheGameSize(); i++) {

            Player currentPlayer = getPlayers().get(getTeamColoursInCurrentOrder().get(i));
            System.out.println(currentPlayer.getPlayerColour() + ", it is your turn to upgrade!");
            System.out.println("You have 2 upgrades in total");

            Map<String,Boolean> upgradedThisSeason = new HashMap<>();
            upgradedThisSeason.put(YOUTH_DEVELOPMENT,false);
            upgradedThisSeason.put(SCOUTING, false);
            upgradedThisSeason.put(STADIUM_INCOME, false);

            for (int j = 0; j < UPGRADES_PER_PLAYER_PER_SEASON; j++) {

                System.out.println("You have " + currentPlayer.getPlayerMoney() + "M.");
                printInfoAboutUpgrades(currentPlayer);
                playerChoiceAndUpgrade(upgradedThisSeason,currentPlayer);
            }
        }
    }

    private static void printTrainingCentreUpgradeRequirements(Player player){

        int playerDevelopmentLevel = player.getPlayerUpgradeBoard().getTrainingCentreLevel();
        System.out.println("1. Upgrade player development");
        if(playerDevelopmentLevel == MAX_LEVEL_UPGRADE){

            System.out.println("You have reached max level of the training centre");
            return;
        }

        System.out.println("   Your current bonuses:");
        System.out.println("   Add max " + playerDevelopmentLevel + " stars to " + playerDevelopmentLevel + " players");
        System.out.println("   Next level:");
        System.out.println("   Add max " + (playerDevelopmentLevel+1) + " stars to " + (playerDevelopmentLevel+1) + " players");
        if(playerDevelopmentLevel == 3) {

            System.out.println("    Add one permanent star to one player");
        }
        System.out.println("   Price: " + player.getPlayerUpgradeBoard().getTrainingCentrePrice() + "M.");
    }

    private static void printScoutingUpgradeRequirements(Player player){

        int playerScoutingLevel = player.getPlayerUpgradeBoard().getScoutingStaffLevel();
        System.out.println("2. Upgrade scouting");
        if(playerScoutingLevel == MAX_LEVEL_UPGRADE){

            System.out.println("You have reached max level of the scouting");
            return;
        }

        System.out.println("   Your current bonuses:");
        System.out.println("   Scout " + playerScoutingLevel + " player");
        System.out.println("   Next level:");
        System.out.println("   Scout " + (playerScoutingLevel+1) + " player");
        System.out.println("   Price: " + player.getPlayerUpgradeBoard().getScoutingUpgradePrice()+ "M.");
    }

    private static void printStadiumUpgradeRequirements(Player player){

        int playerStadiumLevel = player.getPlayerUpgradeBoard().getStadiumLevel();
        System.out.println("3. Upgrade stadium");
        if(playerStadiumLevel == MAX_LEVEL_UPGRADE){

            System.out.println("You have reached max level of the stadium upgrades");
            return;
        }

        System.out.println("   Your current bonuses:");
        player.getPlayerUpgradeBoard().printStadiumIncome();
        System.out.println("   Next level:");
        player.getPlayerUpgradeBoard().printNextLevelUpgradeStadiumIncome();
        System.out.println("   Price: " + player.getPlayerUpgradeBoard().getStadiumUpgradePrice() + "M.");
    }

    private static void printInfoAboutUpgrades(Player currentPlayer){

        printTrainingCentreUpgradeRequirements(currentPlayer);
        printScoutingUpgradeRequirements(currentPlayer);
        printStadiumUpgradeRequirements(currentPlayer);
        System.out.println("4. Skip");
    }

    private static boolean canThisBeUpgraded(Map<String,Boolean> upgradedThisSeason, String toUpgrade, Player player, int price){

        if(upgradedThisSeason.get(toUpgrade)){

            System.out.println("You already upgraded your youth development this season!");
            return false;
        }
        if(player.getPlayerMoney() < price){

            System.out.println("You do not have enough money for that");
            return false;
        }

        return true;
    }
    private static void upgradeFacility(Map<String, Boolean> upgradedThisSeason, String toUpgrade, Player player) throws InterruptedException {

        if(Objects.equals(toUpgrade, YOUTH_DEVELOPMENT)){

            player.spendMoney(player.getPlayerUpgradeBoard().getTrainingCentrePrice());
            player.getPlayerUpgradeBoard().upgradeTrainingCentre();
        }
        else if(Objects.equals(toUpgrade, SCOUTING)){

            player.spendMoney(player.getPlayerUpgradeBoard().getScoutingUpgradePrice());
            player.getPlayerUpgradeBoard().upgradeScoutingStaff();
        }
        else if(Objects.equals(toUpgrade, STADIUM_INCOME)){

            player.spendMoney(player.getPlayerUpgradeBoard().getStadiumUpgradePrice());
            player.getPlayerUpgradeBoard().upgradeStadium();
        }

        upgradedThisSeason.remove(toUpgrade);
        upgradedThisSeason.put(toUpgrade,true);

        System.out.println("The upgrade was purchased successfully");
        Thread.sleep(1000);
    }
    private static boolean upgradeTraining(Map<String, Boolean> upgradedThisSeason, Player player) throws InterruptedException {

        if(!canThisBeUpgraded(upgradedThisSeason,YOUTH_DEVELOPMENT,player, player.getPlayerUpgradeBoard().getTrainingCentrePrice())){

            return false;
        }
        upgradeFacility(upgradedThisSeason,YOUTH_DEVELOPMENT,player);
        return true;
    }

    private static boolean upgradeScouting(Map<String, Boolean> upgradedThisSeason, Player player) throws InterruptedException {

        if(!canThisBeUpgraded(upgradedThisSeason,SCOUTING,player,player.getPlayerUpgradeBoard().getScoutingUpgradePrice())){

            return false;
        }
        upgradeFacility(upgradedThisSeason,SCOUTING,player);
        return true;
    }

    private static boolean upgradeStadium(Map<String, Boolean> upgradedThisSeason, Player player) throws InterruptedException {
        if(!canThisBeUpgraded(upgradedThisSeason,STADIUM_INCOME,player,player.getPlayerUpgradeBoard().getStadiumUpgradePrice())){

            return false;
        }
        upgradeFacility(upgradedThisSeason,STADIUM_INCOME,player);
        return true;
    }

    private static void playerChoiceAndUpgrade(Map<String, Boolean> upgradedThisSeason, Player player) throws InterruptedException {

        int choice = choiceMadeByTheUserValidation(0,4);
        switch (choice){

            case 1 -> {
                if(!upgradeTraining(upgradedThisSeason, player)){

                    playerChoiceAndUpgrade(upgradedThisSeason, player);
                }
            }
            case 2 -> {
                if(!upgradeScouting(upgradedThisSeason, player)){

                    playerChoiceAndUpgrade(upgradedThisSeason, player);
                }
            }
            case 3 -> {
                if(!upgradeStadium(upgradedThisSeason, player)){

                    playerChoiceAndUpgrade(upgradedThisSeason, player);
                }
            }
        }
    }

}

package Game;

import Player.Player;


import java.util.HashMap;
import java.util.Map;

import static Game.Game.*;
import static Game.FinalVariables.*;

public class Upgrades {

    public static void upgrades(){
        for (int i = 0; i < getPlayersInTheGameSize(); i++) {
            Player currentPlayer = players.get(teamColoursInCurrentOrder.get(i));
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
        if(playerDevelopmentLevel == 3)
            System.out.println("    Add one permanent star to one player");
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

    private static boolean upgradeTraining(Map<String, Boolean> upgradedThisSeason, Player player){
        if(upgradedThisSeason.get(YOUTH_DEVELOPMENT)){
            System.out.println("You already upgraded your youth development this season!");
            return false;
        }
        if(player.getPlayerMoney() < player.getPlayerUpgradeBoard().getTrainingCentrePrice()){
            System.out.println("You do not have enough money for that");
            return false;
        }

        player.spendMoney(player.getPlayerUpgradeBoard().getTrainingCentrePrice());
        player.getPlayerUpgradeBoard().upgradeTrainingCentre();
        upgradedThisSeason.remove(YOUTH_DEVELOPMENT);
        upgradedThisSeason.put(YOUTH_DEVELOPMENT,true);
        return true;
    }

    private static boolean upgradeScouting(Map<String, Boolean> upgradedThisSeason, Player player){
        if(upgradedThisSeason.get(SCOUTING)){
            System.out.println("You already upgraded your scouting this season!");
            return false;
        }
        if(player.getPlayerMoney() < player.getPlayerUpgradeBoard().getScoutingUpgradePrice()){
            System.out.println("You do not have enough money for that");
            return false;
        }

        player.spendMoney(player.getPlayerUpgradeBoard().getScoutingUpgradePrice());
        player.getPlayerUpgradeBoard().upgradeScoutingStaff();
        upgradedThisSeason.remove(SCOUTING);
        upgradedThisSeason.put(SCOUTING,true);
        return true;
    }

    private static boolean upgradeStadium(Map<String, Boolean> upgradedThisSeason, Player player){
        if(upgradedThisSeason.get(STADIUM_INCOME)){
            System.out.println("You already upgraded your stadium this season!");
            return false;
        }
        if(player.getPlayerMoney() < player.getPlayerUpgradeBoard().getStadiumUpgradePrice()){
            System.out.println("You do not have enough money for that");
            return false;
        }

        player.spendMoney(player.getPlayerUpgradeBoard().getStadiumUpgradePrice());
        player.getPlayerUpgradeBoard().upgradeStadium();
        upgradedThisSeason.remove(STADIUM_INCOME);
        upgradedThisSeason.put(STADIUM_INCOME,true);
        return true;
    }

    private static void playerChoiceAndUpgrade(Map<String, Boolean> upgradedThisSeason, Player player){

        int choice = choiceMadeByTheUserValidation(0,4);
        switch (choice){
            case 1:
                if(!upgradeTraining(upgradedThisSeason, player)){
                    playerChoiceAndUpgrade(upgradedThisSeason, player);
                }
                break;
            case 2:
                if(!upgradeScouting(upgradedThisSeason, player)){
                    playerChoiceAndUpgrade(upgradedThisSeason, player);
                }
                break;
            case 3:
                if(!upgradeStadium(upgradedThisSeason, player)){
                    playerChoiceAndUpgrade(upgradedThisSeason, player);
                }
                break;
            default:
        }
    }

}

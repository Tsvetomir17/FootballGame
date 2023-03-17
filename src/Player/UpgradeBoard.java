package Player;

import java.util.HashMap;
import java.util.Map;

import static Game.FinalVariables.*;

public class UpgradeBoard {
    private int trainingCentreLevel;
    private int scoutingStaffLevel;
    private int stadiumLevel;
    private int trainingCentrePrice;
    private int scoutingUpgradePrice;
    private int stadiumUpgradePrice;
    private Map<String,Integer> stadiumIncome;
    public UpgradeBoard(){
        trainingCentreLevel = 1;
        scoutingStaffLevel = 1;
        stadiumLevel = 1;
        trainingCentrePrice = FIRST_TRAINING_CENTRE_AND_SCOUTING_UPGRADE_PRICE;
        scoutingUpgradePrice = FIRST_TRAINING_CENTRE_AND_SCOUTING_UPGRADE_PRICE;
        stadiumUpgradePrice = FIRST_STADIUM_UPGRADE_PRICE;
        stadiumIncome = new HashMap<>();
        fillStadiumIncomeStartingPrices();
    }

    private void fillStadiumIncomeStartingPrices(){
        stadiumIncome.put(NEWLY_PROMOTED, NEWLY_PROMOTED_STADIUM_INCOME_STARTING);
        stadiumIncome.put(ESTABLISHED, ESTABLISHED_STADIUM_INCOME_STARTING);
        stadiumIncome.put(MID_TABLE, MID_TABLE_STADIUM_INCOME_STARTING);
        stadiumIncome.put(TITLE_CONTENDER, TITLE_CONTENDER_STADIUM_INCOME_STARTING);
    }

    public int getTrainingCentreLevel() {
        return trainingCentreLevel;
    }

    public int getScoutingStaffLevel() {
        return scoutingStaffLevel;
    }

    public int getStadiumLevel() {
        return stadiumLevel;
    }

    public void upgradeTrainingCentre(){
        if(getTrainingCentreLevel() == MAX_LEVEL_UPGRADE){
            System.out.println("The training centre is upgraded to it's maximum level!");
            return;
        }

        trainingCentreLevel += 1;
        trainingCentrePrice *= INITIAL_STEP_FOR_UPGRADES;
    }

    public void upgradeScoutingStaff(){
        if(getScoutingStaffLevel() == MAX_LEVEL_UPGRADE){
            System.out.println("The scouting staff is upgraded to it's maximum level!");
            return;
        }

        scoutingStaffLevel += 1;
        scoutingUpgradePrice *= INITIAL_STEP_FOR_UPGRADES;
    }

    public void upgradeStadium(){
        if(getStadiumLevel() == MAX_LEVEL_UPGRADE){
            System.out.println("The stadium is upgraded to it's maximum level!");
            return;
        }

        stadiumLevel += 1;
        stadiumUpgradePrice *= INITIAL_STEP_FOR_UPGRADES;

        stadiumIncome = nextLevelStadiumIncome();
    }

    private Map<String, Integer> nextLevelStadiumIncome() {
        Map<String,Integer> newStadiumIncome = new HashMap<>();
        newStadiumIncome.put(NEWLY_PROMOTED, stadiumIncome.get(NEWLY_PROMOTED) + NEWLY_PROMOTED_STADIUM_INCOME_STARTING/2);
        newStadiumIncome.put(ESTABLISHED, stadiumIncome.get(ESTABLISHED) + ESTABLISHED_STADIUM_INCOME_STARTING/2);
        newStadiumIncome.put(MID_TABLE, stadiumIncome.get(MID_TABLE) + MID_TABLE_STADIUM_INCOME_STARTING/2);
        newStadiumIncome.put(TITLE_CONTENDER, stadiumIncome.get(TITLE_CONTENDER) + TITLE_CONTENDER_STADIUM_INCOME_STARTING/2);
        return newStadiumIncome;
    }

    public int getTrainingCentrePrice() {
        return trainingCentrePrice;
    }

    public int getScoutingUpgradePrice() {
        return scoutingUpgradePrice;
    }

    public int getStadiumUpgradePrice() {
        return stadiumUpgradePrice;
    }

    private void printStadiumIncome(Map<String,Integer> stadiumIncome){
        System.out.println("    Newly promoted  " + stadiumIncome.get(NEWLY_PROMOTED) + "M.");
        System.out.println("    Established     " + stadiumIncome.get(ESTABLISHED)  + "M.");
        System.out.println("    Mid-table       " + stadiumIncome.get(MID_TABLE)  + "M.");
        System.out.println("    Title contender " + stadiumIncome.get(TITLE_CONTENDER)  + "M.");
    }
    
    public void printStadiumIncome(){
        printStadiumIncome(this.stadiumIncome);
    }
    public void printNextLevelUpgradeStadiumIncome(){
        if(stadiumLevel == MAX_LEVEL_UPGRADE) return;
        printStadiumIncome(nextLevelStadiumIncome());
    }

    public int getStadiumIncomeForTheCurrentPoints(int currentPlayerPoints){
        if(currentPlayerPoints > 0 && currentPlayerPoints < 40) return stadiumIncome.get(NEWLY_PROMOTED);
        else if(currentPlayerPoints >= 40 && currentPlayerPoints < 60) return stadiumIncome.get(ESTABLISHED);
        else if(currentPlayerPoints >= 60 && currentPlayerPoints < 79) return stadiumIncome.get(MID_TABLE);
        else if(currentPlayerPoints >= 80) return stadiumIncome.get(TITLE_CONTENDER);
        else throw new IllegalArgumentException();
    }

}

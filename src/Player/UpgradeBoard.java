package Player;

public class UpgradeBoard {
    final static int MAX_LEVEL_UPGRADE = 3;

    private int trainingCentreLevel;
    private int scoutingStaffLevel;
    private int stadiumLevel;

    public UpgradeBoard(){
        trainingCentreLevel = 0;
        scoutingStaffLevel = 0;
        stadiumLevel = 0;
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

    public boolean upgradeTrainingCentre(){
        if(getTrainingCentreLevel() == MAX_LEVEL_UPGRADE){
            System.out.println("The training centre is upgraded to it's maximum level!");
            return false;
        }

        trainingCentreLevel += 1;
        return true;
    }

    public boolean upgradeScoutingStaff(){
        if(getScoutingStaffLevel() == MAX_LEVEL_UPGRADE){
            System.out.println("The scouting staff is upgraded to it's maximum level!");
            return false;
        }

        scoutingStaffLevel += 1;
        return true;
    }

    public boolean upgradeStadium(){
        if(getStadiumLevel() == MAX_LEVEL_UPGRADE){
            System.out.println("The stadium is upgraded to it's maximum level!");
            return false;
        }

        stadiumLevel += 1;
        return true;
    }
}

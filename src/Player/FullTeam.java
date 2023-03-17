package Player;

import FootballPlayer.FootballPlayer;
import FootballPlayer.FootballPlayerComparator;
import java.util.ArrayList;
import java.util.List;

import static Game.FinalVariables.MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE;


public class FullTeam {

    private final List<FootballPlayer> fullTeamOfFootballPlayers;

    private double defenceOverall;
    private double midfieldOverall;
    private double attackOverall;

    public FullTeam() {
        this.fullTeamOfFootballPlayers = new ArrayList<>(MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE);
    }

    public FullTeam(FullTeam fullTeam){
        this.fullTeamOfFootballPlayers = new ArrayList<>(fullTeam.fullTeamOfFootballPlayers);
        this.defenceOverall = fullTeam.defenceOverall;
        this.midfieldOverall = fullTeam.midfieldOverall;
        this.attackOverall = fullTeam.attackOverall;
    }

    public void addPlayerToTheTeam(FootballPlayer playerToAdd){
        if(fullTeamOfFootballPlayers.size() == MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE){
            System.out.println("The team is full, you can not add players right now");
            return;
        }
        fullTeamOfFootballPlayers.add(playerToAdd);

        fullTeamOfFootballPlayers.sort(new FootballPlayerComparator());
    }
    public void upgradePlayer(FootballPlayer theUpgradedPlayer, int index){
        removePlayerAtIndex(index);
        addPlayerToTheTeam(theUpgradedPlayer);
    }

    public void removePlayerAtIndex(int index){
        if(index < 0 || index >= fullTeamOfFootballPlayers.size()){
            throw new ArrayIndexOutOfBoundsException();
        }

        fullTeamOfFootballPlayers.remove(index);
    }

    public void printFullTeam(){
        int iterator = 1;
        for (FootballPlayer player : fullTeamOfFootballPlayers) {
            System.out.print((iterator++) + ". ");
            player.printFootballPlayer();
        }
    }
    public int getFullTeamSize(){
        return fullTeamOfFootballPlayers.size();
    }

    public FootballPlayer getFootballPlayerAtIndex(int index){
        return fullTeamOfFootballPlayers.get(index);
    }

    public double getDefenceOverall() {
        return defenceOverall;
    }

    public void setDefenceOverall(double defenceOverall) {
        if(defenceOverall < 0){
            throw new IllegalArgumentException();
        }
        this.defenceOverall = defenceOverall;
    }

    public double getMidfieldOverall() {
        return midfieldOverall;
    }

    public void setMidfieldOverall(double midfieldOverall) {
        if(midfieldOverall < 0){
            throw new IllegalArgumentException();
        }
        this.midfieldOverall = midfieldOverall;
    }

    public double getAttackOverall() {
        return attackOverall;
    }

    public void setAttackOverall(double attackOverall) {
        if(attackOverall < 0){
            throw new IllegalArgumentException();
        }
        this.attackOverall = attackOverall;
    }

    public FootballPlayer selectFootballPlayerForLineUpAtIndex(int index){
        FootballPlayer footballPlayer = fullTeamOfFootballPlayers.get(index);
        removePlayerAtIndex(index);
        return footballPlayer;
    }

    public List<FootballPlayer> getFullTeamOfFootballPlayers(){
        return fullTeamOfFootballPlayers;
    }
}

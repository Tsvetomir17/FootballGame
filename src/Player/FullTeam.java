package Player;

import FootballPlayer.FootballPlayer;

import java.util.ArrayList;
import java.util.List;

import static GameModes.FinalVariables.MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE;


public class FullTeam {

    private List<FootballPlayer> fullTeamOfFootballPlayers;

    public FullTeam() {
        this.fullTeamOfFootballPlayers = new ArrayList<>(MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE);
    }

    public void addPlayerToTheTeam(FootballPlayer playerToAdd){
        if(fullTeamOfFootballPlayers.size() == MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE){
            System.out.println("The team is full, you can not add players right now");
            return;
        }
        fullTeamOfFootballPlayers.add(playerToAdd);
    }

    public void upgradePlayer(FootballPlayer theUpgradedPlayer, int index){
        if(index < 0 || index >= fullTeamOfFootballPlayers.size()){
            throw new ArrayIndexOutOfBoundsException();
        }

        fullTeamOfFootballPlayers.remove(index);
        fullTeamOfFootballPlayers.add(index,theUpgradedPlayer);
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
}

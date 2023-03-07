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

    public boolean addPlayerToTheTeam(FootballPlayer playerToAdd){
        if(fullTeamOfFootballPlayers.size() == MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE){
            System.out.println("The team is full, you can not add players right now");
            return false;
        }
        fullTeamOfFootballPlayers.add(playerToAdd);
        return true;
    }

    public void printFullTeam(){
        for (FootballPlayer player : fullTeamOfFootballPlayers) {
            player.printFootballPlayer();
        }
    }
}

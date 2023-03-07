package Player;

import FootballPlayer.FootballPlayer;

import java.util.ArrayList;
import java.util.List;

import static GameModes.FinalVariables.MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE;


public class FullTeamAndStaff {

//    final static int MAX_KEYSTAFF_IN_TEAM_SIZE = 3;

    private List<FootballPlayer> fullTeamOfFootballPlayers;
//    private List<KeyStaff> keystaff


    public FullTeamAndStaff() {
        this.fullTeamOfFootballPlayers = new ArrayList<>(MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE);
//        this.keystaff = new ArrayList<>(MAX_KEYSTAFF_IN_TEAM_SIZE);
    }

    public boolean addPlayerToTheTeam(FootballPlayer playerToAdd){
        if(fullTeamOfFootballPlayers.size() == MAX_FOOTBALL_PLAYERS_IN_TEAM_SIZE){
            System.out.println("The team is full, you can not add players right now");
            return false;
        }
        fullTeamOfFootballPlayers.add(playerToAdd);
        return true;
    }

    //addKeystaff
}

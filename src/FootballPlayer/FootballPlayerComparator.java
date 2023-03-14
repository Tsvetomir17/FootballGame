package FootballPlayer;

import java.util.Comparator;

public class FootballPlayerComparator implements Comparator<FootballPlayer> {

    @Override
    public int compare(FootballPlayer p1, FootballPlayer p2) {

        int posCompare = p1.getFootballPlayerPosition().compareTo(p2.getFootballPlayerPosition());
        if (posCompare != 0) {
            return posCompare;
        }

        int ratingCompare = Integer.compare(p2.getCurrentFootballPlayerRating(), p1.getCurrentFootballPlayerRating());
        if (ratingCompare != 0) {
            return ratingCompare;
        }

        return p1.getFootballPlayerName().compareTo(p2.getFootballPlayerName());
    }
}




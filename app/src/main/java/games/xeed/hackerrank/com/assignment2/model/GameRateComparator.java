package games.xeed.hackerrank.com.assignment2.model;

import java.util.Comparator;

/**
 * Created by nitinraj.arvind on 7/12/2015.
 */
public class GameRateComparator implements Comparator<GameItem> {

    @Override
    public int compare(GameItem lhs, GameItem rhs) {
        Float flhs = 0.0f;
        try {
            flhs = Float.parseFloat(lhs.getRating());
        }catch (Exception e){
            e.printStackTrace();
        }
        Float frhs = 0.0f;
        try {
            frhs = Float.parseFloat(rhs.getRating());
        }catch (Exception e){
            e.printStackTrace();
        }


        if ( flhs > frhs ) {
            return 1;
        } else if ( flhs < frhs ) {
            return -1;
        } else {
            return 0;
        }

    }
}
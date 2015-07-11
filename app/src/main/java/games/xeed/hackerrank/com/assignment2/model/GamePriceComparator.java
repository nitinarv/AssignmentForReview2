package games.xeed.hackerrank.com.assignment2.model;

import java.util.Comparator;

/**
 * Created by nitinraj.arvind on 7/12/2015.
 */
public class GamePriceComparator implements Comparator<GameItem> {

    @Override
    public int compare(GameItem lhs, GameItem rhs) {
        Float flhs = 0.0f;
        try {
            flhs = Float.parseFloat(lhs.getPrice());
        }catch (Exception e){
            e.printStackTrace();
        }
        Float frhs = 0.0f;
        try {
            frhs = Float.parseFloat(rhs.getPrice());
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

package games.xeed.hackerrank.com.assignment2.model;

import java.util.Comparator;

/**
 * Created by nitinraj.arvind on 7/12/2015.
 */
public class ReverseOrder<T> implements Comparator<T> {
    private Comparator<T> delegate;
    public ReverseOrder(Comparator<T> delegate){
        this.delegate = delegate;
    }

    public int compare(T a, T b) {
        //reverse order of a and b!!!
        return this.delegate.compare(b,a);
    }
}
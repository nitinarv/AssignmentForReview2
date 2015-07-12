package games.xeed.hackerrank.com.assignment2.view;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import games.xeed.hackerrank.com.assignment2.R;
import games.xeed.hackerrank.com.assignment2.model.GameItem;
import games.xeed.hackerrank.com.assignment2.model.GamePriceComparator;
import games.xeed.hackerrank.com.assignment2.model.GameRateComparator;
import games.xeed.hackerrank.com.assignment2.model.ReverseOrder;

/**
 * Created by nitinraj.arvind on 7/12/2015.
 */
public class GameListArrayAdapter extends ArrayAdapter<GameItem>{

    public enum SortRule{
        SORT_BY_RATE_ASC,
        SORT_BY_RATE_DESC,
        SORT_BY_PRICE_ASC,
        SORT_BY_PRICE_DESC
    }

    List<GameItem> objects;
    List<GameItem> resultantObjects;
    GamePriceComparator gamePriceComparator;
    GameRateComparator gameRateComparator;
    SortRule sortRule;


    public GameListArrayAdapter(Context context, int resource, List<GameItem> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.resultantObjects = objects;
        this.gamePriceComparator = new GamePriceComparator();
        this.gameRateComparator  = new GameRateComparator();

        setSortRule(null);
        filterResult(null);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_game_list, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        if(resultantObjects.size()>position) {
            GameItem i = resultantObjects.get(position);

            if (i != null) {

                // This is how you obtain a reference to the TextViews.
                // These TextViews are created in the XML files we defined.

                TextView tt = (TextView) v.findViewById(android.R.id.text1);
                TextView ttd = (TextView) v.findViewById(R.id.price);
                TextView mt = (TextView) v.findViewById(R.id.rating);

                // check to see if each individual textview is null.
                // if not, assign some text!
                if (tt != null) {
                    tt.setText("Name: " + i.getName());
                }
                if (ttd != null) {
                    ttd.setText(", Rating: " + i.getRating());
                }
                if (mt != null) {
                    mt.setText(", Price: " + i.getPrice());
                }

            }
        }
        // the view must be returned to our activity
        return v;
    }

    @Override
    public int getCount() {
        return resultantObjects.size();
    }

    public void filterResult(String query){
        SortTask st = new SortTask(resultantObjects, query);

        st.execute();
    }

    public void setSortRule(SortRule mSortRule){
        if(mSortRule!=null) {
            sortRule = mSortRule;
        }else{
            sortRule = SortRule.SORT_BY_RATE_DESC;
        }
    }

    public GameItem getItemForPosition(int position) {
        GameItem i = null;
        if(resultantObjects.size()>position) {
            i = resultantObjects.get(position);
        }
        return i;
    }

    public void applySortRule(){
        if(sortRule == SortRule.SORT_BY_RATE_DESC){
            Collections.sort(resultantObjects, new ReverseOrder(gameRateComparator));
        }else if(sortRule == SortRule.SORT_BY_RATE_ASC){
            Collections.sort(resultantObjects, gameRateComparator);
        }else if(sortRule == SortRule.SORT_BY_PRICE_DESC){
            Collections.sort(resultantObjects, new ReverseOrder(gamePriceComparator));
        }else if(sortRule == SortRule.SORT_BY_PRICE_ASC){
            Collections.sort(resultantObjects, gamePriceComparator);
        }
        clear();
        notifyDataSetInvalidated();
        notifyDataSetChanged();
    }

    public class SortTask extends AsyncTask<Void, Void, Void>{

        List<GameItem> dGameItemsList;
        String dQuery;
        List<GameItem> dResultantObjects;

        public SortTask(List<GameItem> gameItemsList, String query){
            dGameItemsList = gameItemsList;
            dQuery = query;
        }

        @Override
        protected Void doInBackground(Void... params) {
            if(dQuery!=null && !dQuery.trim().isEmpty()) {
                dResultantObjects = new ArrayList<GameItem>();
                for (GameItem item : dGameItemsList) {
                    if (item.getName().trim().toLowerCase().contains(dQuery.trim().toLowerCase())
                            || item.getRating().trim().toLowerCase().contains(dQuery.trim().toLowerCase())) {
                        dResultantObjects.add(item);
                    }
                }
            }else{
                dResultantObjects = new ArrayList<GameItem>(dGameItemsList);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            resultantObjects = new ArrayList<GameItem>(dResultantObjects);
            applySortRule();
        }
    }

}

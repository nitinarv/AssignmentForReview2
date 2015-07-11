package games.xeed.hackerrank.com.assignment2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import games.xeed.hackerrank.com.assignment2.R;
import games.xeed.hackerrank.com.assignment2.model.GameItem;

/**
 * Created by nitinraj.arvind on 7/12/2015.
 */
public class GameListArrayAdapter extends ArrayAdapter<GameItem>{

    List<GameItem> objects;

    public GameListArrayAdapter(Context context, int resource, List<GameItem> objects) {
        super(context, resource, objects);
        this.objects = objects;
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
        GameItem i = objects.get(position);

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView tt = (TextView) v.findViewById(android.R.id.text1);
            TextView ttd = (TextView) v.findViewById(R.id.price);
            TextView mt = (TextView) v.findViewById(R.id.rating);

            // check to see if each individual textview is null.
            // if not, assign some text!
            if (tt != null){
                tt.setText("Name: "+i.getName());
            }
            if (ttd != null){
                ttd.setText(", Rating: "+i.getRating());
            }
            if (mt != null){
                mt.setText(", Price: "+i.getPrice());
            }

        }

        // the view must be returned to our activity
        return v;
    }
}

package games.xeed.hackerrank.com.assignment2.view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import games.xeed.hackerrank.com.assignment2.R;

/**
 * Created by nitinraj.arvind on 7/11/2015.
 */
public class GamesListViewFragment extends ListFragment {

//    private List<GameListViewItem> mItems;
public static final String TAG_GAME_LIST_FRAGMENT = "games.xeed.hackerrank.com.assignment2.view.GamesListViewFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);
//        content_scrollview = (ScrollView) view.findViewById(R.id.content_scrollview);
//        request_1_result_details = (TextView) view.findViewById(R.id.request_1_result_details);
//        request_2_result_details = (TextView) view.findViewById(R.id.request_2_result_details);
//        request_3_result_details = (TextView) view.findViewById(R.id.request_3_result_details);
//
//        runningTasks = new ArrayList<TruecallerTestTask>();

        String[] tickerSymbols = new String[] { "MSFT", "ORCL", "AMZN", "ERTS" };
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                tickerSymbols));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // retrieve theListView item
//        ListViewItem item = mItems.get(position);
    }
}

package games.xeed.hackerrank.com.assignment2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import games.xeed.hackerrank.com.assignment2.R;
import games.xeed.hackerrank.com.assignment2.controller.OperationCallback;
import games.xeed.hackerrank.com.assignment2.controller.XseedTestTask;
import games.xeed.hackerrank.com.assignment2.model.GameItem;
import games.xeed.hackerrank.com.assignment2.model.GamePriceComparator;
import games.xeed.hackerrank.com.assignment2.model.GameRateComparator;
import games.xeed.hackerrank.com.assignment2.model.ReverseOrder;
import games.xeed.hackerrank.com.assignment2.model.TaskResult;

/**
 * Created by nitinraj.arvind on 7/11/2015.
 */
public class GamesListViewFragment extends ListFragment {

    //    private List<GameListViewItem> mItems;
    public static final String TAG_GAME_LIST_FRAGMENT = "games.xeed.hackerrank.com.assignment2.view.GamesListViewFragment";
    public static final String GAME_DETAIL_EXTRA = "GAME_DETAIL_EXTRA";

    String[] tickerSymbols;
    TaskResult taskResult;
    TaskCallbacks taskCallbacks;
    List<GameItem> gameList;
    TextView game_count;

    interface TaskCallbacks{
        public void onGameItemClicked(GameItem gameItem);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setRetainInstance(true);
        activity = getActivity();
        taskCallbacks = (MainActivity) activity;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);
        game_count = (TextView) view.findViewById(R.id.game_count);

        XseedTestTask x1 = new XseedTestTask(getActivity(), new OperationCallback() {
            @Override
            public void processException(Exception e) {

            }

            @Override
            public void onProgressStarted() {

            }

            @Override
            public void onProgressEnded() {

            }

            @Override
            public void onOperationCancelled() {

            }

            @Override
            public void onProgressUpdated(int progressPercent) {

            }

            @Override
            public void processFinalResult(Object object) {

            }

            @Override
            public void useStringResult(String result) {

            }

            @Override
            public void storeTaskResult(TaskResult mTaskResult) {
                taskResult = mTaskResult;



                GamePriceComparator gamePriceComparator = new GamePriceComparator();
                GameRateComparator gameRateComparator  = new GameRateComparator();
                gameList= taskResult.getGameItemList();

//                Collections.sort(taskResult.getGameItemList(), new GamePriceComparator());
//                Collections.sort(taskResult.getGameItemList(), new ReverseOrder(gamePriceComparator));
//                Collections.sort(gameList, gameRateComparator);
                Collections.sort(taskResult.getGameItemList(), new ReverseOrder(gameRateComparator));

                game_count.setText("Game Count: "+gameList.size());
                setListAdapter(new GameListArrayAdapter(getActivity(), R.layout.item_game_list, gameList));
            }
        });

        x1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);





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
        Intent detailScreenIntent = new Intent(getActivity(), DetailActivity.class);
        GameItem gameItem = gameList.get(position);
        detailScreenIntent.putExtra(GAME_DETAIL_EXTRA,gameItem);
        startActivity(detailScreenIntent);

    }
}

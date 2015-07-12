package games.xeed.hackerrank.com.assignment2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
public class GamesListViewFragment extends ListFragment implements View.OnClickListener{

    public static final String TAG_GAME_LIST_FRAGMENT = "games.xeed.hackerrank.com.assignment2.view.GamesListViewFragment";
    public static final String GAME_DETAIL_EXTRA = "GAME_DETAIL_EXTRA";

    String[] tickerSymbols;
    TaskResult taskResult;
    TaskCallbacks taskCallbacks;
    List<GameItem> gameList;
    GameListArrayAdapter gameListArrayAdapter;


    EditText game_search;
    TextView game_count;
    TextView game_api_count;
    Button button_sort_price;
    Button button_sort_rate;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_sort_price:
                if(gameListArrayAdapter!=null){
                    gameListArrayAdapter.setSortRule(GameListArrayAdapter.SortRule.SORT_BY_PRICE_DESC);
                    gameListArrayAdapter.applySortRule();
                }
                break;
            case R.id.button_sort_rate:
                if(gameListArrayAdapter!=null){
                    gameListArrayAdapter.setSortRule(GameListArrayAdapter.SortRule.SORT_BY_RATE_DESC);
                    gameListArrayAdapter.applySortRule();
                }
                break;
        }

    }

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
        game_search = (EditText) view.findViewById(R.id.game_search);
        game_count = (TextView) view.findViewById(R.id.game_count);
        game_api_count = (TextView) view.findViewById(R.id.game_api_count);
        button_sort_price = (Button) view.findViewById(R.id.button_sort_price);
        button_sort_rate = (Button) view.findViewById(R.id.button_sort_rate);

        button_sort_price.setOnClickListener(this);
        button_sort_rate.setOnClickListener(this);

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

                game_count.setText("Game Count: " + gameList.size());
                gameListArrayAdapter = new GameListArrayAdapter(getActivity(), R.layout.item_game_list, gameList);
                setListAdapter(gameListArrayAdapter);
            }
        });

        x1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        game_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(gameListArrayAdapter!=null && game_search!=null){
                    gameListArrayAdapter.filterResult(game_search.getText().toString());
                }
            }
        });

        return view;
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent detailScreenIntent = new Intent(getActivity(), DetailActivity.class);
        GameItem gameItem = gameListArrayAdapter.getItemForPosition(position);
        if(gameItem==null){
            Toast.makeText(getActivity(), "gameItem is null", Toast.LENGTH_LONG).show();
            return;
        }
        detailScreenIntent.putExtra(GAME_DETAIL_EXTRA,gameItem);
        startActivity(detailScreenIntent);

    }
}

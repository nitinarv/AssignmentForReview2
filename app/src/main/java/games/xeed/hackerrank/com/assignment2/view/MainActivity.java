package games.xeed.hackerrank.com.assignment2.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import games.xeed.hackerrank.com.assignment2.R;
import games.xeed.hackerrank.com.assignment2.controller.OperationCallback;
import games.xeed.hackerrank.com.assignment2.controller.XseedTestTask;
import games.xeed.hackerrank.com.assignment2.model.GameItem;
import games.xeed.hackerrank.com.assignment2.model.TaskResult;


public class MainActivity extends BaseActivity implements  GamesListViewFragment.TaskCallbacks{

    private FrameLayout frameLayout;
    private GamesListViewFragment gamesListViewFragment;
//    private GamesDetailFragment gamesDetailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle(this, getClass());
        gamesListViewFragment = (GamesListViewFragment) getSupportFragmentManager().findFragmentByTag(GamesListViewFragment.TAG_GAME_LIST_FRAGMENT);


        frameLayout = (FrameLayout) findViewById(R.id.game_list_fragment);

        if (savedInstanceState == null) {
            if(gamesListViewFragment==null) {
                gamesListViewFragment = new GamesListViewFragment();
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.game_list_fragment, gamesListViewFragment, GamesListViewFragment.TAG_GAME_LIST_FRAGMENT)
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.bounce_interpolator)
                    .commit();
        }else{
            gamesListViewFragment = (GamesListViewFragment) getSupportFragmentManager().findFragmentByTag(GamesListViewFragment.TAG_GAME_LIST_FRAGMENT);
        }


    }


    @Override
    public void onGameItemClicked(GameItem gameItem) {
//        gamesDetailFragment = (GamesDetailFragment) getSupportFragmentManager().findFragmentByTag(GamesDetailFragment.TAG_GAME_DETAIL_FRAGMENT);
//        if(gamesDetailFragment==null) {
//            gamesDetailFragment = new GamesDetailFragment();
//        }
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.game_list_fragment, gamesDetailFragment, GamesDetailFragment.TAG_GAME_DETAIL_FRAGMENT)
//                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.bounce_interpolator)
//                .commit();
//        setTitle(this, getClass(), getResources().getString(R.string.detail_activity_label));
    }
}

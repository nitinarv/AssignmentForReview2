package games.xeed.hackerrank.com.assignment2.view;

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
import games.xeed.hackerrank.com.assignment2.model.TaskResult;


public class MainActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    private FrameLayout frameLayout;
    private GamesListViewFragment gamesListViewFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                mToolbar.setTitle(R.string.empty_string);
            }
        });


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
//            gamesListViewFragment.resetView();
        }


        XseedTestTask x1 = new XseedTestTask(this, new OperationCallback() {
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
            public void storeTaskResult(TaskResult taskResult) {

            }
        });

        x1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

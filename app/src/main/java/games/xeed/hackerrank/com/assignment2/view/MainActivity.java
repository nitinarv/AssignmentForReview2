package games.xeed.hackerrank.com.assignment2.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import games.xeed.hackerrank.com.assignment2.R;
import games.xeed.hackerrank.com.assignment2.controller.OperationCallback;
import games.xeed.hackerrank.com.assignment2.controller.XseedTestTask;
import games.xeed.hackerrank.com.assignment2.model.GameItem;
import games.xeed.hackerrank.com.assignment2.model.TaskResult;


public class MainActivity extends AppCompatActivity implements  GamesListViewFragment.TaskCallbacks{

    private FrameLayout frameLayout;
    private GamesListViewFragment gamesListViewFragment;


    private Toolbar mToolbar;
    private TextView mToolbarTitle;

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


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


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
    public boolean onCreateOptionsMenu(Menu menu) {
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
        if (id == R.id.action_reload) {
            if(gamesListViewFragment!=null)
                gamesListViewFragment.loadApiList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGameItemClicked(GameItem gameItem) {

    }


    public Toolbar getToolbar() {
        return mToolbar;
    }



    public void setTitle(Context context, Class gClass){
        setTitle(context, gClass, null);
    }

    /**
     * We have to include the toolbar and then we have to
     * set the title usign the pcakage manager
     * */
    public void setTitle(Context context, Class gClass, String mTitle){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        if(mToolbarTitle!=null){
            PackageManager pm = getPackageManager();
            ComponentName componentName = new ComponentName(context, gClass);
            String value = "-NA-";
            if(mTitle==null) {
                try {
                    if (pm.getActivityInfo(componentName, 0).labelRes != 0) {
                        value = context.getResources().getString(pm.getActivityInfo(componentName, 0).labelRes);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }else{
                value = mTitle;
            }
            mToolbarTitle.setText(value);
        }
    }
}

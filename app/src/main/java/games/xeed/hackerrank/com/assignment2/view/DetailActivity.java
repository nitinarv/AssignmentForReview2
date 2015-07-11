package games.xeed.hackerrank.com.assignment2.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import games.xeed.hackerrank.com.assignment2.R;

public class DetailActivity extends BaseActivity {


    private FrameLayout frameLayout;
    private GamesDetailFragment gameDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle(this, getClass());
        gameDetailFragment = (GamesDetailFragment) getSupportFragmentManager().findFragmentByTag(GamesDetailFragment.TAG_GAME_DETAIL_FRAGMENT);


        frameLayout = (FrameLayout) findViewById(R.id.game_list_fragment);

        if (savedInstanceState == null) {
            if(gameDetailFragment==null) {
                gameDetailFragment = new GamesDetailFragment();
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.game_detail_fragment, gameDetailFragment, GamesDetailFragment.TAG_GAME_DETAIL_FRAGMENT)
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.bounce_interpolator)
                    .commit();
        }else{
            gameDetailFragment = (GamesDetailFragment) getSupportFragmentManager().findFragmentByTag(GamesDetailFragment.TAG_GAME_DETAIL_FRAGMENT);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

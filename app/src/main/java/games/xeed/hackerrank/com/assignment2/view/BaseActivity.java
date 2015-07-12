package games.xeed.hackerrank.com.assignment2.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import games.xeed.hackerrank.com.assignment2.R;

/**
 * Created by nitinraj.arvind on 7/11/2015.
 */
public class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mToolbarTitle;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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

    }


    @Override
    public MenuInflater getMenuInflater() {
        return super.getMenuInflater();
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

package games.xeed.hackerrank.com.assignment2.controller;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nitinraj.arvind on 7/12/2015.
 */
public class SharedPrefHandler {

    private static SharedPrefHandler instance = null;
    private Context context = null;

    private SharedPrefHandler(Context context){
        this.context = context;
    }

    public static SharedPrefHandler getInstance(Context context){
        if(instance==null){
            instance = new SharedPrefHandler(context);
        }

        return instance;
    }

    public void upApiHitCount(){
        SharedPreferences sharedPreference = context.getSharedPreferences("API_CONTEXT", Context.MODE_MULTI_PROCESS);
        int apiHitCount = sharedPreference.getInt("COUNT",0);

        SharedPreferences.Editor edit = sharedPreference.edit();
        edit.putInt("COUNT", ++apiHitCount);
        edit.commit();
    }

    public int getApiHitCount(){
        SharedPreferences sharedPreference = context.getSharedPreferences("API_CONTEXT", Context.MODE_MULTI_PROCESS);
        int apiHitCount = sharedPreference.getInt("COUNT",0);
        return apiHitCount;
    }

}

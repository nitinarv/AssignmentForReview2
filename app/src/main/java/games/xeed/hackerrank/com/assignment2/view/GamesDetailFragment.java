package games.xeed.hackerrank.com.assignment2.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.volley.toolbox.NetworkImageView;
import games.xeed.hackerrank.com.assignment2.R;
import games.xeed.hackerrank.com.assignment2.model.GameItem;

/**
 * Created by nitinraj.arvind on 7/11/2015.
 */
public class GamesDetailFragment extends Fragment {

    public static final String TAG_GAME_DETAIL_FRAGMENT = "games.xeed.hackerrank.com.assignment2.view.GamesDetailFragment";
    public GameItem gameItem;

    private NetworkImageView game_icon;
    private TextView game_name;
    private ImageView game_rate_star;
    private TextView game_rate;
    private LinearLayout pie_view;
    private Button button_share;
    private Button button_app_store;
    private Button button_back;
    private Button button_sms;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);
//        content_scrollview = (ScrollView) view.findViewById(R.id.content_scrollview);
//        request_1_result_details = (TextView) view.findViewById(R.id.request_1_result_details);
//        request_2_result_details = (TextView) view.findViewById(R.id.request_2_result_details);
//        request_3_result_details = (TextView) view.findViewById(R.id.request_3_result_details);

//        runningTasks = new ArrayList<TruecallerTestTask>();

        return view;
    }


}

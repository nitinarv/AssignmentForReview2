package games.xeed.hackerrank.com.assignment2.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.android.volley.toolbox.NetworkImageView;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PieChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import games.xeed.hackerrank.com.assignment2.R;
import games.xeed.hackerrank.com.assignment2.controller.VolleySingleton;
import games.xeed.hackerrank.com.assignment2.model.DemographicItem;
import games.xeed.hackerrank.com.assignment2.model.GameItem;

/**
 * Created by nitinraj.arvind on 7/11/2015.
 */
public class GamesDetailFragment extends Fragment implements View.OnClickListener{

    public static final String TAG_GAME_DETAIL_FRAGMENT = "games.xeed.hackerrank.com.assignment2.view.GamesDetailFragment";
    public GameItem gameItem;

    private NetworkImageView game_icon;
    private TextView game_name;
    private RatingBar game_rate_star;
    private TextView game_rate;
    private LinearLayout pie_view;
    private Button button_share;
    private Button button_app_store;
    private Button button_back;
    private Button button_sms;

    TaskCallbacks taskCallbacks;
    DetailActivity activity;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_app_store:
                break;
            case R.id.button_back:
                getActivity().finish();
                break;
            case R.id.button_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String message = DetailActivity.gameItem.getName() +"\n"+DetailActivity.gameItem.getDescription()+"\n"+DetailActivity.gameItem.getUrl();
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.button_sms:
                String message2 = DetailActivity.gameItem.getName() +"\n"+DetailActivity.gameItem.getDescription()+"\n"+DetailActivity.gameItem.getUrl();
                Intent sendIntent2 = new Intent(Intent.ACTION_SENDTO);
                sendIntent2.setData(Uri.parse("sms:"));
                sendIntent2.putExtra("sms_body", message2);
                startActivity(sendIntent2);
                break;
        }
    }

    interface TaskCallbacks{
        public void setGameItem(GameItem gameItem);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setRetainInstance(true);
        activity = getActivity();
        taskCallbacks = (DetailActivity) activity;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);

        game_icon = (NetworkImageView) view.findViewById(R.id.game_icon);
        game_name = (TextView) view.findViewById(R.id.game_name);
        game_rate_star = (RatingBar) view.findViewById(R.id.game_rate_star);
        game_rate = (TextView) view.findViewById(R.id.game_rate);
        pie_view = (LinearLayout) view.findViewById(R.id.pie_view);
        button_share = (Button) view.findViewById(R.id.button_share);
        button_app_store = (Button) view.findViewById(R.id.button_app_store);
        button_back  =(Button) view.findViewById(R.id.button_back);
        button_sms = (Button) view.findViewById(R.id.button_sms);

        button_app_store.setOnClickListener(this);
        button_back.setOnClickListener(this);
        button_share.setOnClickListener(this);
        button_sms.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setView(DetailActivity.gameItem);
    }

    public void setView(GameItem mGameItem){
        gameItem = mGameItem;
        game_icon.setImageUrl(gameItem.getImage(), VolleySingleton.getInstance(getActivity()).getImageLoader());
        game_name.setText(gameItem.getName());
        game_rate.setText(gameItem.getRating());
        game_rate_star.setRating(Float.parseFloat(gameItem.getRating()));

        if(gameItem.getDemographic()!=null && !gameItem.getDemographic().isEmpty()){
            CategorySeries cs = buildCategoryDataset(gameItem.getDemographic());
            PieChart charte = generateGradePieChart(cs);
            int[] colors=new int[]{Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW,Color.CYAN, Color.RED,
                    Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW,Color.CYAN, Color.RED,
                    Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW,Color.CYAN, Color.RED,
                    Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW,Color.CYAN, Color.RED,
                    Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW,Color.CYAN, Color.RED};

            DefaultRenderer renderer=new DefaultRenderer();
            renderer.setLabelsTextSize(30);
            renderer.setLegendTextSize(30);
            renderer.setMargins(new int[]{20, 30, 15, 0});

            for(int i = 0 ; i < gameItem.getDemographic().size(); i++){
                SimpleSeriesRenderer r=new SimpleSeriesRenderer();
                r.setColor(colors[i % colors.length]);
                renderer.addSeriesRenderer(r);
            }

            renderer.setZoomButtonsVisible(true);
            renderer.setZoomEnabled(true);
            renderer.setChartTitleTextSize(40);
            renderer.setLabelsColor(Color.BLACK);
            renderer.setShowLabels(true);

            org.achartengine.GraphicalView chartView = ChartFactory.getPieChartView(getActivity(), cs, renderer);
            chartView.repaint();
            pie_view.addView(chartView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        }


    }



    protected CategorySeries buildCategoryDataset(List<DemographicItem> listDemo ){
        CategorySeries series=new CategorySeries("File");

        for(DemographicItem item: listDemo){
            series.add(item.getCountry() + "("+ item.getPercentage()+"%)", Double.parseDouble(item.getPercentage()));
        }

        return series;
    }

    private PieChart generateGradePieChart(CategorySeries series){
        int[] colors=new int[]{Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW,Color.CYAN, Color.RED};
        DefaultRenderer renderer=new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (  int color : colors) {
            SimpleSeriesRenderer r=new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);

        return new PieChart(series,renderer);
    }
}

package com.appspot.mycookooapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appspot.mycookooapp.api.eventEndpoint.model.Event;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecipesActivity extends AppCompatActivity {

    private TextView showTitle1;
    private TextView showTitle2;
    private TextView showTitle3;
    private TextView showTitle4;
    private TextView showTitle5;

    private TextView showTime1;
    private TextView showTime2;
    private TextView showTime3;
    private TextView showTime4;
    private TextView showTime5;

    private TextView showCal1;
    private TextView showCal2;
    private TextView showCal3;
    private TextView showCal4;
    private TextView showCal5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        showTitle1 = (TextView)findViewById(R.id.title1);
        showTitle2 = (TextView)findViewById(R.id.title2);
        showTitle3 = (TextView)findViewById(R.id.title3);
        showTitle4 = (TextView)findViewById(R.id.title4);
        showTitle5 = (TextView)findViewById(R.id.title5);

        showTime1 = (TextView)findViewById(R.id.time1);
        showTime2 = (TextView)findViewById(R.id.time2);
        showTime3 = (TextView)findViewById(R.id.time3);
        showTime4 = (TextView)findViewById(R.id.time4);
        showTime5 = (TextView)findViewById(R.id.time5);

        showCal1 = (TextView)findViewById(R.id.cal1);
        showCal2 = (TextView)findViewById(R.id.cal2);
        showCal3 = (TextView)findViewById(R.id.cal3);
        showCal4 = (TextView)findViewById(R.id.cal4);
        showCal5 = (TextView)findViewById(R.id.cal5);

    }
    @Override
    protected void onStart(){
        super.onStart();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }
    @Override
    protected void onResume() {
        super.onResume();
        int i = ((CookooMainApplication) this.getApplication()).callId;
        BasicFunctions bf = new BasicFunctions();
        List<Event> myEventList = null;
        try {
            myEventList = new RefreshAsyncTask(this).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<SuggestedRecipe> mySRecipeList = bf.recipeParsing(myEventList.get(myEventList.size()-1-i).getSuggestedRecipes());
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        if(mySRecipeList!=null) {
            if (mySRecipeList.size() >= 5) {
                findViewById(R.id.imageView5).setVisibility(View.VISIBLE);
                imageLoader.displayImage(mySRecipeList.get(4).getImage(), (ImageView) findViewById(R.id.imageView5));
                showTitle5.setText(String.valueOf(mySRecipeList.get(4).getTitle()));
                showTime5.setText(String.valueOf(mySRecipeList.get(4).getReadyInMinutes()) + " mins");
                showCal5.setText(String.valueOf(mySRecipeList.get(4).getCalories()) + " cal");
            }
            if (mySRecipeList.size() >= 4) {
                findViewById(R.id.imageView4).setVisibility(View.VISIBLE);
                imageLoader.displayImage(mySRecipeList.get(3).getImage(), (ImageView) findViewById(R.id.imageView4));
                showTitle4.setText(String.valueOf(mySRecipeList.get(3).getTitle()));
                showTime4.setText(String.valueOf(mySRecipeList.get(3).getReadyInMinutes()) + " mins");
                showCal4.setText(String.valueOf(mySRecipeList.get(3).getCalories()) + " cal");
            }
            if (mySRecipeList.size() >= 3) {
                findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
                imageLoader.displayImage(mySRecipeList.get(2).getImage(), (ImageView) findViewById(R.id.imageView3));
                showTitle3.setText(String.valueOf(mySRecipeList.get(2).getTitle()));
                showTime3.setText(String.valueOf(mySRecipeList.get(2).getReadyInMinutes()) + " mins");
                showCal3.setText(String.valueOf(mySRecipeList.get(2).getCalories()) + " cal");
            }
            if (mySRecipeList.size() >= 2) {
                findViewById(R.id.imageView2).setVisibility(View.VISIBLE);
                imageLoader.displayImage(mySRecipeList.get(1).getImage(), (ImageView) findViewById(R.id.imageView2));
                showTitle2.setText(String.valueOf(mySRecipeList.get(1).getTitle()));
                showTime2.setText(String.valueOf(mySRecipeList.get(1).getReadyInMinutes()) + " mins");
                showCal2.setText(String.valueOf(mySRecipeList.get(1).getCalories()) + " cal");
            }
            if (mySRecipeList.size() >= 1) {
                findViewById(R.id.imageView1).setVisibility(View.VISIBLE);
                imageLoader.displayImage(mySRecipeList.get(0).getImage(), (ImageView) findViewById(R.id.imageView1));
                showTitle1.setText(String.valueOf(mySRecipeList.get(0).getTitle()));
                showTime1.setText(String.valueOf(mySRecipeList.get(0).getReadyInMinutes()) + " mins");
                showCal1.setText(String.valueOf(mySRecipeList.get(0).getCalories()) + " cal");
            }
        }
    }
    @Override
    protected void onStop(){
    super.onStop();
        ImageLoader.getInstance().destroy();
    }
}

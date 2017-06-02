package com.appspot.mycookooapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.appspot.mycookooapp.api.eventEndpoint.model.Event;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

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

        int i = ((CookooMainApplication) this.getApplication()).callId;

        List<Event> myEventList = ((CookooMainApplication) this.getApplication()).getGlobalEventList();
        BasicFunctions bf = new BasicFunctions();
        List<SuggestedRecipe> mySRecipeList = bf.recipeParsing(myEventList.get(i).getSuggestedRecipes());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance

        imageLoader.displayImage(mySRecipeList.get(0).getImage(),(ImageView)findViewById(R.id.imageView1));
        imageLoader.displayImage(mySRecipeList.get(1).getImage(),(ImageView)findViewById(R.id.imageView2));
        imageLoader.displayImage(mySRecipeList.get(2).getImage(),(ImageView)findViewById(R.id.imageView3));
        imageLoader.displayImage(mySRecipeList.get(3).getImage(),(ImageView)findViewById(R.id.imageView4));
        imageLoader.displayImage(mySRecipeList.get(4).getImage(),(ImageView)findViewById(R.id.imageView5));

        showTitle1.setText(String.valueOf(mySRecipeList.get(0).getTitle()));
        showTitle2.setText(String.valueOf(mySRecipeList.get(1).getTitle()));
        showTitle3.setText(String.valueOf(mySRecipeList.get(2).getTitle()));
        showTitle4.setText(String.valueOf(mySRecipeList.get(3).getTitle()));
        showTitle5.setText(String.valueOf(mySRecipeList.get(4).getTitle()));

        showTime1.setText(String.valueOf(mySRecipeList.get(0).getReadyInMinutes())+" mins");
        showTime2.setText(String.valueOf(mySRecipeList.get(1).getReadyInMinutes())+" mins");
        showTime3.setText(String.valueOf(mySRecipeList.get(2).getReadyInMinutes())+" mins");
        showTime4.setText(String.valueOf(mySRecipeList.get(3).getReadyInMinutes())+" mins");
        showTime5.setText(String.valueOf(mySRecipeList.get(4).getReadyInMinutes())+" mins");

        showCal1.setText(String.valueOf(mySRecipeList.get(0).getCalories())+" cal");
        showCal2.setText(String.valueOf(mySRecipeList.get(1).getCalories())+" cal");
        showCal3.setText(String.valueOf(mySRecipeList.get(2).getCalories())+" cal");
        showCal4.setText(String.valueOf(mySRecipeList.get(3).getCalories())+" cal");
        showCal5.setText(String.valueOf(mySRecipeList.get(4).getCalories())+" cal");
    }

}

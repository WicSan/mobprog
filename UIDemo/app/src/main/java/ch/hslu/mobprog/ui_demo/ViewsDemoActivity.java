package ch.hslu.mobprog.ui_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by sandr on 12.03.2018.
 */

public class ViewsDemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views_demo);

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
                TextView lblRating = (TextView)findViewById(R.id.lblRating);
                lblRating.setText("Bewertung: " + rating);
            }
        });
    }
}

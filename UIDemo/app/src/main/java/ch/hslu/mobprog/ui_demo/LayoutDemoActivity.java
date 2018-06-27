package ch.hslu.mobprog.ui_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sandr on 08.03.2018.
 */

public class LayoutDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String layout = getIntent().getStringExtra("layout");
        if(layout.equals("linear")) {
            setContentView(R.layout.layoutdemo_linearlayout);
        } else{
            setContentView(R.layout.layoutdemo_constraintlayout);
        }
    }
}

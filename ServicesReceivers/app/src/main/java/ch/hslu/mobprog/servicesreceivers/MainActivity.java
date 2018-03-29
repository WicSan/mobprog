package ch.hslu.mobprog.servicesreceivers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickServiceStart(View button){
        Intent service = new Intent(this, DemoService.class);
        startService(service);
    }

    public void onClickServiceStop(View button){
        stopService(new Intent(this, DemoService.class));
    }

    public void onClickIntentServiceStart(View button){
        Intent service = new Intent(this, DemoService.class);
        startService(service);
    }
}

package ch.hslu.mobprog.servicesreceivers;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by sandr on 27.03.2018.
 */

public class DemoIntentService extends IntentService {
    private int count = 0;

    public DemoIntentService(){
        super("DemoIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        count++;
        Log.e("HSLU Mobprog", String.format("Job#%d started", count));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("HSLU Mobprog", String.format("Job#%d finished", count));
    }

    @Override
    public void onCreate() {
        Log.e("HSLU Mobprog", "Create service.");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e("HSLU Mobprog", "Destroy service.");
        super.onDestroy();
    }
}

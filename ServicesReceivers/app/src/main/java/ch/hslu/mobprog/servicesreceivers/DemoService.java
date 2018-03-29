package ch.hslu.mobprog.servicesreceivers;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by sandr on 27.03.2018.
 */

public class DemoService extends Service {
    private int count = 0;

    @Override
    public void onCreate() {
        Log.e("HSLU Mobprog", "Create service.");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("HSLU Mobprog", "Start command:" + count);
        new DemoTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("HSLU Mobprog", "Destroy service.");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

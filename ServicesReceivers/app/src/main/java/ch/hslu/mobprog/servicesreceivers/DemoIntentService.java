package ch.hslu.mobprog.servicesreceivers;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by sandr on 27.03.2018.
 */

public class DemoIntentService extends IntentService {
    private int count = 0;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DemoIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("HSLU Mobprog", "Start command:" + count);
        new DemoTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

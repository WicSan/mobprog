package ch.hslu.mobprog.servicesreceivers;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sandr on 27.03.2018.
 */

public class DemoService extends Service implements Counter {
    private int count = 0;
    private AtomicInteger startedCount = new AtomicInteger();
    private AtomicInteger completedCount = new AtomicInteger();

    @Override
    public void onCreate() {
        Log.e("HSLU Mobprog", "Create service.");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("HSLU Mobprog", "Start command:" + count++);
        new DemoTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("HSLU Mobprog", "Destroy service.");
        super.onDestroy();
    }

    public void incrementStarted(){
        startedCount.incrementAndGet();
    }

    public void incrementFinished(){
        completedCount.incrementAndGet();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new DemoServiceBinder();
    }

    public class DemoServiceBinder extends Binder implements DemoServiceApi {

        @Override
        public int getNumberOfJobsRunning() {
            return startedCount.get() - completedCount.get();
        }

        @Override
        public int getNumberOfJobsCompleted() {
            return completedCount.get();
        }

        @Override
        public void resetCounters() {
            startedCount.set(0);
            completedCount.set(0);
        }
    }
}

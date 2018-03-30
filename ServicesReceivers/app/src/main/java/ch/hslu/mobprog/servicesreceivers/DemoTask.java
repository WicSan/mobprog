package ch.hslu.mobprog.servicesreceivers;

import android.bluetooth.BluetoothClass;
import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sandr on 27.03.2018.
 */

public class DemoTask extends AsyncTask<Void, Void, Void> {
    private Counter service;

    public DemoTask(Counter service){
        this.service = service;
    }

    @Override
    protected void onPreExecute() {
        service.incrementStarted();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Log.e("HSLU Mobprog","Job running.");
            Thread.sleep(7000);
            Log.e("HSLU Mobprog", "Job finished.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        service.incrementFinished();
    }
}

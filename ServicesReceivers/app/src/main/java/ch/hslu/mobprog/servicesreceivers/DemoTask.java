package ch.hslu.mobprog.servicesreceivers;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by sandr on 27.03.2018.
 */

public class DemoTask extends AsyncTask<Void, Void, Void> {

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
}

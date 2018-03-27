package com.example.sandr.communication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandr on 27.03.2018.
 */

public class MultiAsyncTask extends AsyncTask<URL, String, Void> {
    private Context context;
    private List<String> strings = new ArrayList<String>();
    public MultiAsyncTask(Context context) { this.context = context; }

    @Override
    protected Void doInBackground(URL... urls) {
        for(URL url : urls) {
            try {
                InputStream stream = HttpUtil.openHttpConnection(url);
                String name = BufferUtil.readText(stream);
                strings.add(name);
                publishProgress(name);
                Thread.sleep(2000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        String[] items = strings.toArray(new String[strings.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Film Titel")
            .setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
        Dialog d = dialogBuilder.create();
        d.show();
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Toast.makeText(context, values[0], Toast.LENGTH_SHORT).show();
        super.onProgressUpdate(values);
    }
}

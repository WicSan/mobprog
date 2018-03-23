package com.example.sandr.communication;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by sandr on 21.03.2018.
 */

public class HttpDocumentTask extends AsyncTask<URL, Void, String>{
    private TextView outputTextView;

    public HttpDocumentTask(TextView outputTextView){
        this.outputTextView = outputTextView;
    }

    @Override
    protected String doInBackground(URL... urls) {
        try {
            InputStream stream = HttpUtil.openHttpConnection(urls[0]);
            return BufferUtil.readText(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        outputTextView.setText(s);
        super.onPostExecute(s);
    }
}

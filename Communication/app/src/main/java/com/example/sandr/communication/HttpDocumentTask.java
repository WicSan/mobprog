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
        StringBuilder builder = new StringBuilder();

        try {
            InputStream stream = HttpUtil.openHttpConnection(urls[0]);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

            String line;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        outputTextView.setText(s);
        super.onPostExecute(s);
    }
}

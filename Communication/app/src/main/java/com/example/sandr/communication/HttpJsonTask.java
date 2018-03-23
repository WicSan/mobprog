package com.example.sandr.communication;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by sandr on 22.03.2018.
 */

public class HttpJsonTask extends AsyncTask<URL, Void, JSONArray> {
    private TextView outputTextView;

    public HttpJsonTask(TextView outputTextView){
        this.outputTextView = outputTextView;
    }

    @Override
    protected JSONArray doInBackground(URL... urls) {
        JSONArray array = new JSONArray();
        try {
            InputStream stream = HttpUtil.openHttpConnection(urls[0]);
            array = getJSONArray(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    }

    private JSONArray getJSONArray(InputStream inputStream) throws IOException {
        String json = BufferUtil.readText(inputStream);
        try {
            return new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONArray array) {
        try {
            JSONObject firstObject = array.getJSONObject(0);
            JSONObject definitionObject = firstObject.getJSONArray("lfs").getJSONObject(0);
            outputTextView.setText(String.format("HTTP: %s (since %d)", definitionObject.getString("lf")
                , definitionObject.getInt("since")));
        } catch (JSONException e) {
            outputTextView.setText("Error");
        }
        super.onPostExecute(array);
    }
}

package com.example.sandr.communication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by sandr on 21.03.2018.
 */

public class HttpImageTask extends AsyncTask<URL, Void, Bitmap>{
    private ImageView outputImageView;

    public HttpImageTask(ImageView outputImageView){
        this.outputImageView = outputImageView;
    }

    @Override
    protected Bitmap doInBackground(URL... urls) {
        Bitmap bitmap = null;

        try {
            InputStream stream = HttpUtil.openHttpConnection(urls[0]);
            bitmap = BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        outputImageView.setImageBitmap(bitmap);
        super.onPostExecute(bitmap);
    }
}

package com.example.sandr.communication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sandr on 21.03.2018.
 */

public class HttpDemosActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
    }

    public void onClickGetImage(View button){
        ImageView imageView = findViewById(R.id.image);
        HttpImageTask task = new HttpImageTask(imageView);
        try {
            task.execute(new URL("http://wherever.ch/hslu/homer.jpg"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void onClickGetDocument(View button){
        TextView textView = findViewById(R.id.documentData);
        HttpDocumentTask task = new HttpDocumentTask(textView);
        try {
            task.execute(new URL("http://wherever.ch/hslu/loremIpsum.txt"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void onClickGetJSON(View button){
        TextView textView = findViewById(R.id.json);
        HttpJsonTask task = new HttpJsonTask(textView);
        try {
            task.execute(new URL("http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HTTP"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void onClickGetXML(View button){
        TextView textView = findViewById(R.id.xml);
        HttpXMLTask task = new HttpXMLTask(textView);
        try {
            task.execute(new URL("http://services.aonaware.com/DictService/DictService.asmx/Define?word=android"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

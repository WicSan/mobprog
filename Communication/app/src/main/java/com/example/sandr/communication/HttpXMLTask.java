package com.example.sandr.communication;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by sandr on 22.03.2018.
 */

public class HttpXMLTask extends AsyncTask<URL, Void, Document> {
    private TextView outputTextView;

    public HttpXMLTask(TextView outputTextView){
        this.outputTextView = outputTextView;
    }

    @Override
    protected Document doInBackground(URL... urls) {
        Document document = null;
        try {
            InputStream stream = HttpUtil.openHttpConnection(urls[0]);
            document = getXMLDocument(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return document;
    }

    private Document getXMLDocument(InputStream inputStream) throws Exception {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDocument = builder.parse(inputStream);
            xmlDocument.getDocumentElement().normalize();
            return xmlDocument;
        } catch (ParserConfigurationException | SAXException e) {
            throw new Exception("XML parsing failed");
        }
    }

    @Override
    protected void onPostExecute(Document document) {
        Element firstDefinition = (Element)document.getElementsByTagName("Definition")
                .item(0);
        Node dictName = firstDefinition.getElementsByTagName("Name").item(0);
        Node wordDef = firstDefinition.getElementsByTagName("WordDefinition").item(0);
        outputTextView.setText(String.format("android %s [%s]", wordDef.getTextContent()
            , dictName.getTextContent()));
        super.onPostExecute(document);
    }
}

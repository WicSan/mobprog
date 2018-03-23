package com.example.sandr.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sandr on 22.03.2018.
 */

public class BufferUtil {
    public static String readText(InputStream in) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while((line = reader.readLine()) != null){
            builder.append(line);
            builder.append("\n");
        }

        reader.close();
        return  builder.toString();
    }
}

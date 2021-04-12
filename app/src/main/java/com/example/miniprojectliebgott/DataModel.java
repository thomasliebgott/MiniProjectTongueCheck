package com.example.miniprojectliebgott;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){
        listTongue = new ArrayList<>();
    }
    int listIndex = 0;

    public void saveToFile(Context context){
        try{
            OutputStream stream = context.openFileOutput("tongue.txt", context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            for (Tongue t:listTongue) { writer.write(t.getFeeling()+";"+ t.getDay()+";"+ t.getTypeOfTongue()+"\n");
            }
            writer.flush();
            writer.close();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadFromFile(Context context){
        try{
            InputStream stream = context.openFileInput("tongue.txt");
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine())!=null){
                String []information = line.split(";");
                listTongue.add(
                        new Tongue(information[0],
                                information[1],
                                information[2])
                );
            }
            reader.close();
            streamReader.close();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static DataModel getInstance() {
        return instance;
    }
    ArrayList<Tongue>listTongue = new ArrayList<>();

}

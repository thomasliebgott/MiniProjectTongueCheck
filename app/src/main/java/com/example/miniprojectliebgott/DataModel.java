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
        listTongue.add(new Tongue("andre", "14 april","Light Pink"));
        listTongue.add(new Tongue("thomas", "13 april","Light Pink"));
        listTongue.add(new Tongue("jose", "12 april","Light Pink"));
        listTongue.add(new Tongue("edouart", "11 april","Light Pink"));
        listTongue.add(new Tongue("lea", "10 april","Light Pink"));
        listTongue.add(new Tongue("arthur", "9 april","Light Pink"));
        listTongue.add(new Tongue("kerrian", "8 april","Light Pink"));
        listTongue.add(new Tongue("francois", "7 april","Light Pink"));
        listTongue.add(new Tongue("chloé", "6 april","Light Pink"));
        listTongue.add(new Tongue("maxime", "5 april","Light Pink"));
        listTongue.add(new Tongue("julian", "4 april","Light Pink"));
        listTongue.add(new Tongue("remy", "3 april","Light Pink"));
        listTongue.add(new Tongue("joseph", "2 april","Light Pink"));
        listTongue.add(new Tongue("joseph", "1 april","Light Pink"));
        listTongue.add(new Tongue("joseph", "31 mars","Light Pink"));
        listTongue.add(new Tongue("joseph", "30 mars","Light Pink"));
        listTongue.add(new Tongue("joseph", "29 mars","Light Pink"));
        listTongue.add(new Tongue("joseph", "28 mars","Light Pink"));
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

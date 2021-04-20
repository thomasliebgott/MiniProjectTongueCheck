package com.example.miniprojectliebgott;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DataModel {
    // creation of the unique instance
    private static DataModel instance = new DataModel();

    //Creation of the private constructor
    private DataModel(){
    }

    // creation of the list of index for the recycler view
    int listIndex = 0;

    // This function create a text file which will store the data of our tongue
    // the context is useful to access to a file
    public void saveToFile(Context context){
        // we make a try catch to manage with the fact that we cannot have acces to the file
        try{
            OutputStream stream = context.openFileOutput("tongue.txt", context.MODE_PRIVATE); // with mode private the other activites don't have acces to it
            OutputStreamWriter writer = new OutputStreamWriter(stream); //with streamWritter we can open the buffer to write
            for (Tongue t:listTongue) { writer.write(t.getFeeling()+";"+ t.getDay()+";"+ t.getTypeOfTongue()+"\n"); //we take the items of the class Tongue with the getteur and setteur
            }
            writer.flush(); // make sure we send the data to the file itself
            writer.close(); // close the file to be sure it will not be corrupted
            stream.close(); // close the stream
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // This function allow us to read the data on the file tongue, it's use on the recyclerView to complete the informations
    // the context is useful to access to a file
    public void loadFromFile(Context context){
        try{
            // we make a try catch to manage with the fact that we cannot have acces to the file
            InputStream stream = context.openFileInput("tongue.txt");
            InputStreamReader streamReader = new InputStreamReader(stream);//with streamWritter we can open the buffer to write
            BufferedReader reader = new BufferedReader(streamReader); // allow us to read the files in chunks to read gradually the file
            String line;
            while ((line = reader.readLine())!=null){ // while we have something on the file not null we read the file
                String []information = line.split(";"); // split the line with the character ;
                listTongue.add( // add a new tongue
                        new Tongue(information[0],
                                information[1],
                                information[2])
                );
            }
            reader.close(); // close reader
            streamReader.close(); // close streamReader
            stream.close(); // close stream
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // point of initilaisation of the unique instance of the singleton
    public static DataModel getInstance() {
        return instance;
    }

    // creation of the list of object Tongue call in the recycler view and store on the text file
    ArrayList<Tongue>listTongue = new ArrayList<>();

}

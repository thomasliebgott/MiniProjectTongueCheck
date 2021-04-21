package com.example.miniprojectliebgott;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TongueDetail extends AppCompatActivity {

    // get the references of the editText and the spinner button and imgShow
    EditText editTextDatee;
    EditText editTextFeeling;
    Spinner spinnerTongueType;
    private Button buttonPicture;
    private ImageView imgShowPicture;
    // path of the picture
    private String picturePath = null;

    private static final int RETURN_TAKE_PICTURE = 1;

    @Override
    // initialisation of the function generate when the activity is create
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongue_detail);
        editTextDatee = findViewById(R.id.editTextDatee);
        editTextFeeling = findViewById(R.id.editTextFeeling);
        spinnerTongueType = findViewById(R.id.spinnerTongueType);
        initActivity();
    }

    //Create an activity to start the "picture" part
    private void initActivity(){
        buttonPicture = findViewById(R.id.buttonPicture); //search the id of the button to take a picture
        imgShowPicture = findViewById(R.id.imgShowPicture); //search the id of the picture which will be replace
        createOnclickBtnPicture(); //apply the function when the button is clicked
    }

    //the function when the button is clicked
    private void createOnclickBtnPicture(){
        buttonPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission();  //apply the function to allow the camera to open
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void checkCameraPermission(){ //the function to allow the camera to open
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            if(checkSelfPermission(Manifest.permission.CAMERA) !=
                    PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.CAMERA},100); //check the validity of the permission
            }else{
                takePicture(); //if there is no problem, else we can take a picture and apply this function
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) { //Check the request permissions to the users
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePicture(); //if there is no problem the permission is granted
            }else{
                Toast.makeText(this,"Camera permisson denied",Toast.LENGTH_LONG)
                        .show(); //else the permission is denied
            }
        }
    }

    private void takePicture(){ //the function to take a picture
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //create the intent
        if(intent.resolveActivity(getPackageManager()) != null){ //if there is an "information" here a picture we can go save it
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); //create the time
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES); //save the picture taken in photoDir
            try { //save the picture taken with its time and replace the tongue picture with the picture taken
                File photoFile = File.createTempFile("picture" + time, ".jpg", photoDir);
                picturePath = photoFile.getAbsolutePath();
                Uri pictureUri = FileProvider.getUriForFile(TongueDetail.this,
                        "com.example.miniprojectliebgott.fileprovider",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                startActivityForResult(intent, RETURN_TAKE_PICTURE); //function to replace the 2 pictures
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//create the function to replace teh 2 pictures
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RETURN_TAKE_PICTURE && resultCode==RESULT_OK){//if all its ok
            Bitmap picture = BitmapFactory.decodeFile(picturePath); //we take the path where the picture is saved
            imgShowPicture.setImageBitmap(picture); //we replace the 2
        }
    }

    // call the activity whenever the user go to the application
    @Override
    protected void onResume() {
        super.onResume();
        int index = DataModel.getInstance().listIndex; // get the index which was clicked in the recycler view of the class DailyCheck
        if(index >=0){ // if the index is not null set the editTextDate and editTextfelling and the spinner with the value already save in the file tongue.txt
            Tongue listTongue = DataModel.getInstance().listTongue.get(index);
            editTextDatee.setText(listTongue.getDay());
            editTextFeeling.setText(listTongue.getFeeling());
            for (int i = 0; i < spinnerTongueType.getCount(); i++) { // set the spinner with the value store on the file tongue.txt
                if (spinnerTongueType.getItemAtPosition(i).equals(listTongue.getTypeOfTongue())) {
                    spinnerTongueType.setSelection(i);
                    break;
                }
            }
        }
    }

    // this function allow us to save the data when the user press the button back
    @Override
    public void onBackPressed() {
        // get the text on the editText and spinner and build them into string
        String date = editTextDatee.getText().toString();
        String feeling = editTextFeeling.getText().toString();
        String tongueType = spinnerTongueType.getSelectedItem().toString();
        // if the date text and feeling text are complete we will save them into the dataModel
        if (date.length() > 1 && feeling.length() > 1){
            int index = DataModel.getInstance().listIndex;
            if(index < 0) {
                //add a new Tongue
                DataModel.getInstance().listTongue.add(0,new Tongue(feeling,date,tongueType));
            }else{
                //set the tongue
                DataModel.getInstance().listTongue.set(index, new Tongue(feeling,date,tongueType));
            }
            DataModel.getInstance().saveToFile(TongueDetail.this); // here we save the file by calling the function saveTofile declarated in the datamodel when we changed finished to initialised the data
            finish(); // close the activity
        }else{ // if the user don't put anything in the edit date and feeling we will alert him with an alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(TongueDetail.this); // use the alert dialog and build it
            builder.setTitle(android.R.string.dialog_alert_title); // set the title of the dialog
            builder.setMessage("Some fields are empty"+ " If you close it now you will lose your data"); // set the message of the dialog
            builder.setNegativeButton(android.R.string.no,null); // create a negative button
            builder.setPositiveButton(android.R.string.yes, // create the button yes
                    new DialogInterface.OnClickListener() { // so if the user click on ok it will finish the activity
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog dialog = builder.create(); // build the alert dialog
            dialog.show(); // dialog show to the user
        }
    }
}
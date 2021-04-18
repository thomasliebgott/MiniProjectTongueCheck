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

    // define the ID of each componenents
    EditText editTextDatee;
    EditText editTextFeeling;
    Spinner spinnerTongueType;

    private static final int RETURN_TAKE_PICTURE = 1;
    private Button buttonPicture;
    private ImageView imgShowPicture;
    private String picturePath = null;

    @Override
    // intialisation of the function generate when the activity is create
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongue_detail);
        editTextDatee = findViewById(R.id.editTextDatee);
        editTextFeeling = findViewById(R.id.editTextFeeling);
        spinnerTongueType = findViewById(R.id.spinnerTongueType);
        initActivity();

        buttonPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
        

    }

    private void initActivity() {
        buttonPicture = findViewById(R.id.buttonPicture);
        imgShowPicture = findViewById(R.id.imgShowPicture);
        //createOnclickBtnPicture();
    }

    //@RequiresApi(api = Build.VERSION_CODES.M)
    void checkCameraPermission(){
        if(ContextCompat.checkSelfPermission(TongueDetail.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(TongueDetail.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePicture();
            }else{
                Toast.makeText(this,"Camera permisson denied",Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("picture" + time, ".jpg", photoDir);
                picturePath = photoFile.getAbsolutePath();
                Uri pictureUri = FileProvider.getUriForFile(TongueDetail.this,
                        "com.example.miniprojectliebgott.fileprovider",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                startActivityForResult(intent, RETURN_TAKE_PICTURE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RETURN_TAKE_PICTURE && resultCode==RESULT_OK){
            Bitmap picture = BitmapFactory.decodeFile(picturePath);
            imgShowPicture.setImageBitmap(picture);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int index = DataModel.getInstance().listIndex;
        if(index >=0){
            Tongue listTongue = DataModel.getInstance().listTongue.get(index);
            editTextDatee.setText(listTongue.getDay());
            editTextFeeling.setText(listTongue.getFeeling());
            for (int i = 0; i < spinnerTongueType.getCount(); i++) {
                if (spinnerTongueType.getItemAtPosition(i).equals(listTongue.getTypeOfTongue())) {
                    spinnerTongueType.setSelection(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        String date = editTextDatee.getText().toString();
        String feeling = editTextFeeling.getText().toString();
        String tongueType = spinnerTongueType.getSelectedItem().toString();
        if (date.length() > 1 && feeling.length() > 1){
            int index = DataModel.getInstance().listIndex;
            if(index < 0) {
                //add a new Tongue
                DataModel.getInstance().listTongue.add(0,new Tongue(feeling,date,tongueType));
            }else{
                //set the tongue
                DataModel.getInstance().listTongue.set(index, new Tongue(feeling,date,tongueType));
            }
            DataModel.getInstance().saveToFile(TongueDetail.this); // save to file
            finish();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(TongueDetail.this);
            builder.setTitle(android.R.string.dialog_alert_title);
            builder.setMessage("Some fields are empty"+ " If you close it now you will lose your data");
            builder.setNegativeButton(android.R.string.no,null);
            builder.setPositiveButton(android.R.string.yes,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
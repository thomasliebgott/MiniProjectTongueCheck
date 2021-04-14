package com.example.miniprojectliebgott;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class TongueDetail extends AppCompatActivity {
    EditText editTextDatee;
    EditText editTextFeeling;
    Spinner spinnerTongueType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongue_detail);
        editTextDatee = findViewById(R.id.editTextDatee);
        editTextFeeling = findViewById(R.id.editTextFeeling);
        spinnerTongueType = findViewById(R.id.spinnerTongueType);
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
            //TODO arriver a initialiser le spinner avec valeur
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
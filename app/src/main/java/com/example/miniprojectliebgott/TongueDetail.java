package com.example.miniprojectliebgott;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class TongueDetail extends AppCompatActivity {
    EditText editTextDate;
    EditText editTextFeeling;
    Spinner spinnerTongueType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongue_detail);
        editTextDate = findViewById(R.id.editTextDatee);
        editTextFeeling = findViewById(R.id.editTextFeeling);
        spinnerTongueType = findViewById(R.id.spinnerTongueType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int index = DataModel.getInstance().listIndex;
        if(index >=0){
            Tongue listTongue = DataModel.getInstance().listTongue.get(index);
            editTextDate.setText(listTongue.getDay());
            editTextFeeling.setText(listTongue.getFeeling());
            //TODO arriver a initialiser le spinner avec valeur
        }
    }

    @Override
    public void onBackPressed() {
        String date = editTextDate.getText().toString();
        String feeling = editTextFeeling.getText().toString();
        String tongueType = spinnerTongueType.getSelectedItem().toString();
        if (date.length() > 1 && feeling.length() > 1){
            int index = DataModel.getInstance().listIndex;
            if(index < 0) {
                //save to DataModel
                DataModel.getInstance().listTongue.add(
                        new Tongue(feeling,tongueType,date)
                );
            }else{
                DataModel.getInstance().listTongue.set(index,
                        new Tongue(feeling,tongueType,date));
            }
            DataModel.getInstance().saveToFile(TongueDetail.this);
            finish();
        }else{
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(TongueDetail.this);
            builder.setTitle(android.R.string.dialog_alert_title);
            builder.setMessage("Some fields are empty"+
                    " If you close it now you will lose your data");
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
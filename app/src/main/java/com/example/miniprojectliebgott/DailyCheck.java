package com.example.miniprojectliebgott;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class DailyCheck extends AppCompatActivity {

    // get the references of the editText and the spinner button and imgShow
    RecyclerView recyclerView_Tongue;
    // put the adapter on the recyclerview bu creating it
    DailyCheckAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check);
        recyclerView_Tongue = findViewById(R.id.recyclerView_Tongue);
        adapter = new DailyCheckAdapter(); // create the adapter
        recyclerView_Tongue.setAdapter(adapter); // say to the recycler view to use this adapter
        DataModel.getInstance().loadFromFile(DailyCheck.this); // allow us to load the data on the file
        //set appearance of the recycle view
        recyclerView_Tongue.setLayoutManager(new LinearLayoutManager(DailyCheck.this));
        //indicate when we click to go on tongueDetail
        adapter.setOnItemClickListener(new DailyCheckAdapter.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                goToDetailActivity(position);
            }
        });
            //TODO
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return 0;
            }
            // TODO
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            //TODO onSwiped ou to delete
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();
                Tongue listTongue =
                        DataModel.getInstance().listTongue.get(index);
                DataModel.getInstance().listTongue.remove(index);
                adapter.notifyItemRemoved(index);
                DataModel.getInstance().saveToFile(DailyCheck.this);

                View content = findViewById(android.R.id.content);
                Snackbar.make(content,
                        "day "+listTongue.getDay()+" was deleted", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DataModel.getInstance().
                                        listTongue.add(index,listTongue);
                                adapter.notifyItemInserted(index);
                                DataModel.getInstance().saveToFile(DailyCheck.this); // here we save the file by calling the function saveTofile declarated in the datamodel when we changed the
                            }
                        })
                        .show();
            }
        }
        );
        itemTouchHelper.attachToRecyclerView(recyclerView_Tongue);
    }

    // call the activity whenever the user go to the application
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged(); //notity it that the data have been changed
    }
    // open the activity TongueDetails without information because we choose an index of -1 to tell to the index list that it represent any type of Tongue, and it's a new one
    public void buttonAddClicked(View view){
        goToDetailActivity(-1);
    }

    // open the detail activity
    void goToDetailActivity(int index){
        DataModel.getInstance().listIndex = index;
        Intent intent = new Intent(
                DailyCheck.this,TongueDetail.class);
        startActivity(intent);
    }


}
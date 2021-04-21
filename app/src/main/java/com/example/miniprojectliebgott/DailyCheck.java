package com.example.miniprojectliebgott;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT) {// tell to the app to move left with on swiped and up and down
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                        int fromPos = viewHolder.getAdapterPosition();// the original position of the item that was changed
                        int toPos = target.getAdapterPosition(); // the target position

                        // modify the value of the recycler view
                        Tongue fromTongue = DataModel.getInstance().listTongue.get(fromPos);
                        Tongue toTongue = DataModel.getInstance().listTongue.get(toPos);

                        //get the position of the data on the file tongue.txt
                        DataModel.getInstance().listTongue.set(fromPos,toTongue);
                        DataModel.getInstance().listTongue.set(toPos,fromTongue);

                        adapter.notifyItemMoved(fromPos,toPos); // with the good animation
                        DataModel.getInstance().saveToFile(DailyCheck.this); // save the  position of the data on the file tongue.txt

                        return false;

                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int index = viewHolder.getAdapterPosition();
                        Tongue tongueDetail = DataModel.getInstance().listTongue.get(index);
                        DataModel.getInstance().listTongue.remove(index); // select the good recycler view
                        adapter.notifyItemRemoved(index); // notify that the item was remove
                        DataModel.getInstance().saveToFile(DailyCheck.this); // save the modification of the delete
                        //create a snack bar to advertise the user when he delete something
                        View content = findViewById(android.R.id.content); // to be sure to be on top of everything else
                        Snackbar.make(content,"Item was delete",Snackbar.LENGTH_SHORT).setAction("cancel", new View.OnClickListener() {
                            @Override

                            // this part will reUpload the file
                            public void onClick(View v) {
                                DataModel.getInstance().listTongue.add(index,tongueDetail);
                                adapter.notifyItemInserted(index);
                                DataModel.getInstance().saveToFile(DailyCheck.this); // save the modification of the delete
                            }
                        }).show();

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
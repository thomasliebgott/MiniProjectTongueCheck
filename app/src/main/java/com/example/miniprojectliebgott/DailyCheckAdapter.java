package com.example.miniprojectliebgott;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DailyCheckAdapter extends RecyclerView.Adapter<DailyCheckAdapter.ViewHolder> {


    @NonNull
    @Override
    //fct creation view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        return null;
    }
    //fct put context on the view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    //fct cb items on a dans le screen
    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}

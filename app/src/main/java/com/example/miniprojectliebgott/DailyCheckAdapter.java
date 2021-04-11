package com.example.miniprojectliebgott;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DailyCheckAdapter extends RecyclerView.Adapter<DailyCheckAdapter.ViewHolder> {


    @NonNull
    @Override
    //fct creation view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // creer un itente
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_recyclerviewtong,parent,false);
        ViewHolder viewHolder = new ViewHolder((itemView));
        return viewHolder;
    }

    //fct put context on the view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tongue tongues = DataModel.getInstance().listTongue.get(position);
        holder.textViewDate.setText(tongues.getDay());
        //TODO ajouter holder pour spinner

    }

    //fct cb items on a dans le screen
    @Override
    public int getItemCount() {
        return DataModel.getInstance().listTongue.size();
    }

    // va stocker les valeurs des langues
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewDate;
        TextView textViewTypeOfTongue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            // TODO ajouter text view type de tongue en fonction spinner
        }

    }
}
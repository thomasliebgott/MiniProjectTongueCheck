package com.example.miniprojectliebgott;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DailyCheckAdapter extends RecyclerView.Adapter<DailyCheckAdapter.ViewHolder> {

    // creation of our function onClick
    public interface ClickListener{
        void onItemClick(View view,int position);
    }

    private static ClickListener clickListener;

    public void setOnItemClickListener(ClickListener clickListener){
        DailyCheckAdapter.clickListener = clickListener;
    }

    @NonNull
    @Override

    //boite pour controler tout les composant a controler
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // creer un intente
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_recyclerviewtong,parent,false);
        ViewHolder viewHolder = new ViewHolder((itemView));
        return viewHolder;
    }

    //mise a jour de chaque model avec les infos sur la date en question
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tongue tongues = DataModel.getInstance().listTongue.get(position);
        holder.textViewDate.setText(tongues.getDay());
        holder.textViewTypeOfTongue.setText(tongues.getTypeOfTongue());
    }

    //renvoie le nb d'items on a dans le screen
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
            textViewTypeOfTongue = itemView.findViewById(R.id.textViewTypeOfTongue);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener != null){
                        clickListener.onItemClick(itemView,getAdapterPosition());
                    }
                }
            });
        }

    }
}

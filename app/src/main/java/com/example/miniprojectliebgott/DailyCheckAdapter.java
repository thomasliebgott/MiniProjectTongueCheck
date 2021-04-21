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
    // box to controle the components of the controller
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // creer un intente
        Context context = parent.getContext(); // we create a context with the getContext which is not an activity so we have to get it from the parent viewGroup
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_recyclerviewtong,parent,false); // get the layout and root the elements
        ViewHolder viewHolder = new ViewHolder((itemView)); // get the view holder creater by the itemView
        return viewHolder;
    }

    // update each model with the information on the date and type of tongue on the recycler View
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tongue tongues = DataModel.getInstance().listTongue.get(position); // get the object tongue
        //get the holder of our components
        holder.textViewDate.setText(tongues.getDay());
        holder.textViewTypeOfTongue.setText(tongues.getTypeOfTongue());
    }

    //give the number of items that we will have on the screen
    @Override
    public int getItemCount() {
        return DataModel.getInstance().listTongue.size();
    }

    // create the class View Holder
    // this class will store the values of the tongues
    public class ViewHolder extends RecyclerView.ViewHolder{
        // reference of the edit text on the layout
        TextView textViewDate;
        TextView textViewTypeOfTongue;

        // create the constructor of the ViewHolderClasse
        // allow us to change the contend of the view holder

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate); // we get the textView from the itemView before
            textViewTypeOfTongue = itemView.findViewById(R.id.textViewTypeOfTongue);

            // this part allow us to clic on the recycler view to open the activity TongueDetail.java
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

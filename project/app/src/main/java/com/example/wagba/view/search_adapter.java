package com.example.wagba.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.Model.card_restaurent_meal;
import com.example.wagba.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class search_adapter extends RecyclerView.Adapter<search_adapter.viewholder> {

    private ArrayList<card_restaurent_meal> List = new ArrayList<>();

    public search_adapter(ArrayList<card_restaurent_meal> list) {
        List = list;
    }


    private search_adapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void  onItemClick(int position);
    }

    public search_adapter.OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(search_adapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card, parent, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.name.setText(List.get(position).getName());
        holder.details.setText(List.get(position).getDetails());
        Picasso.get().load(List.get(position).getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<card_restaurent_meal> moviesList) {
        this.List = List;
        notifyDataSetChanged();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView name,details;
        ImageView img;
        public viewholder(@NonNull View itemView , final search_adapter.OnItemClickListener listner) {
            super(itemView);
            img=itemView.findViewById(R.id.search_img);
            name=itemView.findViewById(R.id.search_title);
            details =itemView.findViewById(R.id.search_details);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listner != null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION);{
                            listner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

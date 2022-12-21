package com.example.wagba.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;
import com.example.wagba.database.meal;

import java.util.ArrayList;

public class add_to_card_adapter extends RecyclerView.Adapter<add_to_card_adapter.view_holder> {

    private ArrayList<meal> List = new ArrayList<>();

    public add_to_card_adapter(ArrayList<meal> list) {
        List = list;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new view_holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_to_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.name.setText(List.get(position).getName());
        holder.details.setText(List.get(position).getDetails());
        //holder.img.setImageResource(List.get(position).getImgl());
        holder.price.setText(List.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<meal> moviesList) {
        this.List = List;
        notifyDataSetChanged();
    }

    public static class view_holder extends RecyclerView.ViewHolder {
        TextView name,details,price;
        ImageView img;
        public view_holder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.meal_card_img);
            name=itemView.findViewById(R.id.meal_card_title);
            details =itemView.findViewById(R.id.meal_card_data);
            price =itemView.findViewById(R.id.meal_card_price);
        }
    }
}

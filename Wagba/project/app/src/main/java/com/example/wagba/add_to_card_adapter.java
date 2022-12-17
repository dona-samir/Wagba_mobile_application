package com.example.wagba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
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
        holder.img.setImageResource(List.get(position).getImg());
        holder.price.setText(Double.toString(List.get(position).getPrice()));
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

            img=itemView.findViewById(R.id.meal_img);
            name=itemView.findViewById(R.id.meal_title);
            details =itemView.findViewById(R.id.meal_data);
            price =itemView.findViewById(R.id.meal_price);
        }
    }
}

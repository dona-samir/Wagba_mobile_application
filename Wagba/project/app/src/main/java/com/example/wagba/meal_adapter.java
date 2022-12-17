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

public class meal_adapter extends RecyclerView.Adapter<meal_adapter.Viewholder> {

    private ArrayList<meal> List = new ArrayList<>();

    public meal_adapter(ArrayList<meal> list) {
        List = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.name.setText(List.get(position).getName());
        holder.details.setText(List.get(position).getDetails());
        holder.img.setImageResource(List.get(position).getImg());
        holder.price.setText(Double.toString(List.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<meal> List) {
        this.List = List;
        notifyDataSetChanged();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView name,details,price;
        ImageView img;
        public Viewholder(@NonNull View itemView) {

            super(itemView);
            img=itemView.findViewById(R.id.meal_img);
            name=itemView.findViewById(R.id.meal_title);
            details =itemView.findViewById(R.id.meal_data);
            price =itemView.findViewById(R.id.meal_price);

        }
    }
}

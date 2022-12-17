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

public class featured_meal_adapter extends RecyclerView.Adapter<featured_meal_adapter.viewHolder> {

    private ArrayList<meal> List = new ArrayList<>();

    public featured_meal_adapter(ArrayList<meal> list) {
        List = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_meal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(List.get(position).getName());
        holder.img.setImageResource(List.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<meal> moviesList) {
        this.List = List;
        notifyDataSetChanged();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.feature_meal_image);
            name=itemView.findViewById(R.id.feature_meal_name);
        }
    }
}

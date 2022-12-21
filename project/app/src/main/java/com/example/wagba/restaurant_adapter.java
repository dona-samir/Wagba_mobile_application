package com.example.wagba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class restaurant_adapter extends RecyclerView.Adapter<restaurant_adapter.ViewHolder> {

    private ArrayList<restaurant> List = new ArrayList<>();

    public restaurant_adapter(ArrayList<restaurant> list) {
        List = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.resturant_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(List.get(position).getName());
        holder.details.setText(List.get(position).getdetails());
        holder.img.setImageResource(List.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<restaurant> List) {
        this.List = List;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,details;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            img=itemView.findViewById(R.id.resturant_img);
            name=itemView.findViewById(R.id.resturent_title);
            details =itemView.findViewById(R.id.resturant_data);
        }
    }
}

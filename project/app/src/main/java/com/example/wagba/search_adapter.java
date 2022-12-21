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

public class search_adapter extends RecyclerView.Adapter<search_adapter.viewholder> {

    private ArrayList<restaurant> List = new ArrayList<>();

    public search_adapter(ArrayList<restaurant> list) {
        List = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.name.setText(List.get(position).getName());
        holder.details.setText(List.get(position).getdetails());
        holder.img.setImageResource(List.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<restaurant> moviesList) {
        this.List = List;
        notifyDataSetChanged();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView name,details;
        ImageView img;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.search_img);
            name=itemView.findViewById(R.id.search_title);
            details =itemView.findViewById(R.id.resturant_data);
        }
    }
}

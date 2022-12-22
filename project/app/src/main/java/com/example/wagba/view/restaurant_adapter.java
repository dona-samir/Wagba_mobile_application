package com.example.wagba.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;
import com.example.wagba.database.restaurant;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.Collections;

public class restaurant_adapter extends RecyclerView.Adapter<restaurant_adapter.ViewHolder> {

    private ArrayList<restaurant> List = new ArrayList<>();

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void  onItemClick(int position);
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public restaurant_adapter(ArrayList<restaurant> list) {
        List = list;
    }

    public restaurant_adapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.resturant_card ,parent, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(List.get(position).getName());
        holder.details.setText(List.get(position).getDetails());
        Picasso.get().load(List.get(position).getImg_bg()).into(holder.img);
        Picasso.get().load(List.get(position).getImg()).into(holder.logo);
        holder.delivery.setText(List.get(position).getDelivery_fee());
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
        TextView name,details , delivery;
        ImageView img,logo;
        public ViewHolder(@NonNull View itemView , final OnItemClickListener listner) {

            super(itemView);
            img=itemView.findViewById(R.id.reastaurant_card_img);
            name=itemView.findViewById(R.id.resturent_card_title);
            details =itemView.findViewById(R.id.resturant_card_data);
            delivery = itemView.findViewById(R.id.restaurant_page_delivery_price);
            logo=itemView.findViewById(R.id.reastuarant_card_logo);

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

            Log.d("adapter_img",img.toString());
        }
    }
}

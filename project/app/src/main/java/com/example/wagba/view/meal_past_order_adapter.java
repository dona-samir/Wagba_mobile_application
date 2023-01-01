package com.example.wagba.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.Model.meal_order;
import com.example.wagba.R;
import com.example.wagba.Model.meal;

import java.util.ArrayList;

public class meal_past_order_adapter extends RecyclerView.Adapter<meal_past_order_adapter.viewholder> {

    private ArrayList<meal_order> List = new ArrayList<>();

    public meal_past_order_adapter(ArrayList<meal_order> list) {
        List = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meals_past_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.name.setText(List.get(position).getName());
        holder.details.setText(List.get(position).getDetails());
        holder.price.setText(List.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<meal_order> List) {
        this.List = List;
        notifyDataSetChanged();
    }

    public static class viewholder extends RecyclerView.ViewHolder {

        TextView name, details , price;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.meal_card_title);
            details =itemView.findViewById(R.id.meal_card_data);
            price =itemView.findViewById(R.id.meal_card_price);
        }    }
}

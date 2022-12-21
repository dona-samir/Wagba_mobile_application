package com.example.wagba.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;
import com.example.wagba.database.order;

import java.util.ArrayList;

public class orders_list_adapter extends RecyclerView.Adapter<orders_list_adapter.viewHolder> {

    private ArrayList<order> List = new ArrayList<>();

    public orders_list_adapter(ArrayList<order> list) {
        List = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(List.get(position).getRes_title());
        holder.img.setImageResource(List.get(position).getRes_img());
        holder.id.setText(List.get(position).getOrder_id());
        holder.status.setText(List.get(position).getOrder_status());
        holder.date.setText(List.get(position).getOrder_date());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<order> moviesList) {
        this.List = List;
        notifyDataSetChanged();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView name,status , id , date;
        ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.restaurant_img);
            name=itemView.findViewById(R.id.order_restaurant_title);
            status =itemView.findViewById(R.id.order_status);
            id =itemView.findViewById(R.id.order_id);
            date =itemView.findViewById(R.id.order_date);
        }
    }
}

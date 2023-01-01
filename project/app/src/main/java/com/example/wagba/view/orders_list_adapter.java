package com.example.wagba.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.Model.cart_data;
import com.example.wagba.R;

import java.util.ArrayList;

public class orders_list_adapter extends RecyclerView.Adapter<orders_list_adapter.viewHolder> {

    private ArrayList<cart_data> List = new ArrayList<>();

    public orders_list_adapter(ArrayList<cart_data> list) {
        List = list;
    }

    private orders_list_adapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void  onItemClick(int position);
    }

    public orders_list_adapter.OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(orders_list_adapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public orders_list_adapter() {
    }

    public ArrayList<cart_data> getList() {
        return List;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card, parent, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(List.get(position).getRestaurant_name());
        //holder.img.setImageResource(List.get(position).getRes_img());
        holder.id.setText(List.get(position).getId());
        holder.status.setText(List.get(position).getState());
        holder.date.setText(List.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<cart_data> List) {
        this.List = List;
        notifyDataSetChanged();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView name,status , id , date;
        ImageView img;
        ImageButton btn;
        public viewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            //img=itemView.findViewById(R.id.restaurant_img);
            name=itemView.findViewById(R.id.order_card_restaurant_title);
            status =itemView.findViewById(R.id.order_card_order_status);
            id =itemView.findViewById(R.id.order_card_order_id);
            date =itemView.findViewById(R.id.order_card_order_date);
            btn = itemView.findViewById(R.id.order_card_open_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION);{
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}

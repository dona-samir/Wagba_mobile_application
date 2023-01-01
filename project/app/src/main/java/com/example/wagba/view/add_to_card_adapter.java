package com.example.wagba.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;
import com.example.wagba.Model.meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class add_to_card_adapter extends RecyclerView.Adapter<add_to_card_adapter.view_holder> {

    private Map<meal, Long> counted ;
   // Map<meal, Long> counted;

    public add_to_card_adapter(Map<meal, Long> list) {
        counted = list;
       // counted = List.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
    public add_to_card_adapter() {
    }

    public interface OnItemClickListener{
        void  onItemClick(int position);
    }

    private add_to_card_adapter.OnItemClickListener addlistener;
    private add_to_card_adapter.OnItemClickListener removelistener;


    public add_to_card_adapter.OnItemClickListener getaddListener() {
        return addlistener;
    }
    public add_to_card_adapter.OnItemClickListener getRemovelistener() {
        return removelistener;
    }


    public void add(int position){
        meal key = (meal) counted.keySet().toArray()[position];
        Long num = counted.get(key);
        counted.replace(key, num + 1);
        notifyDataSetChanged();
    }
    public void setAddlistener(add_to_card_adapter.OnItemClickListener addlistener) {
        this.addlistener = addlistener;
    }
    public void setRemovelistener(add_to_card_adapter.OnItemClickListener removelistener) {
        this.removelistener = removelistener;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new view_holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_to_card, parent, false),addlistener,removelistener);
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {

            meal key = (meal) counted.keySet().toArray()[position];
            Long num = counted.get(key);
            holder.name.setText(key.getName());
            holder.details.setText(key.getDetails());
            Picasso.get().load(key.getImg()).into(holder.img);
            holder.price.setText(key.getPrice());
            holder.itemcount.setText(num.toString());

    }

    @Override
    public int getItemCount() {
        return counted.size();
    }

    public void setList(Map<meal, Long> List) {
        this.counted = List;

        notifyDataSetChanged();
    }

    public static class view_holder extends RecyclerView.ViewHolder {
        TextView name,details,price , itemcount;
        ImageView img;
        AppCompatImageButton add,remove;
        public view_holder(@NonNull View itemView , final OnItemClickListener addlistener, final OnItemClickListener removelistener) {
            super(itemView);

            img=itemView.findViewById(R.id.meal_card_img);
            name=itemView.findViewById(R.id.meal_card_title);
            details =itemView.findViewById(R.id.meal_card_data);
            price =itemView.findViewById(R.id.meal_card_price);
            itemcount = itemView.findViewById(R.id.add_to_cart_meal_inumber);
            add = itemView.findViewById(R.id.add_to_cart_add_btn);
            remove = itemView.findViewById(R.id.add_to_cart_remove_btn);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(addlistener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION);
                        {
                            addlistener.onItemClick(position);

                        }
                }
            }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(removelistener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION);
                        {
                            removelistener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}

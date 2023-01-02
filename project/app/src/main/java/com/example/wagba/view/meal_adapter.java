package com.example.wagba.view;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;
import com.example.wagba.Model.meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class meal_adapter extends RecyclerView.Adapter<meal_adapter.Viewholder> {

    private ArrayList<meal> List = new ArrayList<>();

    public meal_adapter(ArrayList<meal> list) {
        List = list;
    }

    private meal_adapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void  onItemClick(int position);
    }

    public meal_adapter.OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public meal_adapter() {
        
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_card, parent, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.name.setText(List.get(position).getName());
        holder.details.setText(List.get(position).getDetails());
        Picasso.get().load(List.get(position).getImg()).into(holder.img);
        holder.price.setText(List.get(position).getPrice());
        if(List.get(position).getAvailable()==0){
           holder.btn.setBackgroundColor(Color.parseColor("#FFBD8C2C"));
        }
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<meal> List) {
        Log.d("meals0",List.toString());
        this.List = List;
        notifyDataSetChanged();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView name,details,price;
        ImageView img;
        Button btn;
        public Viewholder(@NonNull View itemView ,final OnItemClickListener listner) {

            super(itemView);
            img=itemView.findViewById(R.id.meal_card_img);
            name=itemView.findViewById(R.id.meal_card_title);
            details =itemView.findViewById(R.id.meal_card_data);
            price =itemView.findViewById(R.id.meal_card_price);
            btn = itemView.findViewById(R.id.meal_card_addToCard_btn);

            btn.setOnClickListener(new View.OnClickListener() {
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
//
        }
    }
}

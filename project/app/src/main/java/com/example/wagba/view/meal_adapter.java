package com.example.wagba.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;
import com.example.wagba.database.meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class meal_adapter extends RecyclerView.Adapter<meal_adapter.Viewholder> {

    private ArrayList<meal> List = new ArrayList<>();

    public meal_adapter(ArrayList<meal> list) {
        List = list;
    }

//    private meal_adapter.OnItemClickListener listener;
//
//    public interface OnItemClickListener{
//        void  onItemClick(int position);
//    }
//
//    public meal_adapter.OnItemClickListener getListener() {
//        return listener;
//    }
//
//    public void setListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }

    public meal_adapter() {
        
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
        Picasso.get().load(List.get(position).getImg()).into(holder.img);
        holder.price.setText(List.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setList(ArrayList<meal> List) {
        this.List = List;
        notifyDataSetChanged();
    }

    public void setList() {
        this.List = List;
        notifyDataSetChanged();
    }
    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView name,details,price;
        ImageView img;
        public Viewholder(@NonNull View itemView) {

            super(itemView);
            img=itemView.findViewById(R.id.meal_card_img);
            name=itemView.findViewById(R.id.meal_card_title);
            details =itemView.findViewById(R.id.meal_card_data);
            price =itemView.findViewById(R.id.meal_card_price);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(listner != null){
//                        int position = getAdapterPosition();
//                        if(position!= RecyclerView.NO_POSITION);{
//                            listner.onItemClick(position);
//                        }
//                    }
//                }
//            });
//
        }
    }
}

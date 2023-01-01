package com.example.wagba.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.R;
import com.example.wagba.Model.meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class featured_meal_adapter extends RecyclerView.Adapter<featured_meal_adapter.viewHolder> {

    private ArrayList<meal> List = new ArrayList<>();

    public featured_meal_adapter(){}

    public featured_meal_adapter(ArrayList<meal> list) {
        List = list;
    }

    private featured_meal_adapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void  onItemClick(int position);
    }

    public featured_meal_adapter.OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(featured_meal_adapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_meal, parent, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(List.get(position).getName());
        Picasso.get().load(List.get(position).getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return List.size();

    }

    public void setList(ArrayList<meal> List) {
        this.List = List;
        notifyDataSetChanged();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView img;
        public viewHolder(@NonNull View itemView , final featured_meal_adapter.OnItemClickListener listner) {
            super(itemView);
            img=itemView.findViewById(R.id.feature_meal_image);
            name=itemView.findViewById(R.id.feature_meal_name);

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
        }
    }
}

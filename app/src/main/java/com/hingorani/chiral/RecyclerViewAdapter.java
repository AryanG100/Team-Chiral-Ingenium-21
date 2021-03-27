package com.hingorani.chiral;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    String[] names;
    Context context;

    public RecyclerViewAdapter(Context context,String[] names){
        this.names = names;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        RecyclerViewAdapter.MyViewHolder myViewHolder = new MyViewHolder(cardView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(names[position]);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,stats.class);
                intent.putExtra("name",names[position]);
                intent.putExtra("age",Home.ages[position]);
                intent.putExtra("gender",Home.gender[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.name);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}

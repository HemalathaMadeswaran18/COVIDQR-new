package com.example.covidqr_new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList time,date,location;

    public MyAdapter(Context context, ArrayList time, ArrayList date, ArrayList location) {
        this.context = context;
        this.time = time;
        this.date = date;
        this.location = location;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
    holder.date_id.setText(String.valueOf(date.get(position)));
        holder.time_id.setText(String.valueOf(time.get(position)));
        holder.loc_id.setText(String.valueOf(location.get(position)));
    }

    @Override
    public int getItemCount() {
        return this.date.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date_id, time_id, loc_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date_id = itemView.findViewById(R.id.date_tv) ;
            time_id = itemView.findViewById(R.id.time_tv) ;
            loc_id = itemView.findViewById(R.id.location_tv) ;


        }
    }
}

package com.example.alita_pc.alikawulansari_1202152332_studycase5;

/**
 * Created by Alita-pc on 3/25/2018.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class todoadapter extends RecyclerView.Adapter<todoadapter.holder> {
    private Context cont;
    private List<itemtodo> items;
    int id;

    public todoadapter(Context cont, List<itemtodo> items, int id){
        this.cont = cont;
        this.items = items;
        this.id = id;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cont).inflate(R.layout.cv_item, parent, false);
        holder hold = new holder(v);
        return hold;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {
        itemtodo satuan = items.get(position);
        holder.name.setText(satuan.getName());
        holder.description.setText(satuan.getDescription());
        holder.priority.setText(satuan.getPriority());
        holder.cd.setCardBackgroundColor(cont.getResources().getColor(this.id));
    }
    public itemtodo getItem(int position){
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeditem(int i){
        items.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, items.size());
    }

    class holder extends RecyclerView.ViewHolder{
        public TextView name, description, priority;
        public CardView cd;
        public holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.todoname);
            description = itemView.findViewById(R.id.tododesc);
            priority = itemView.findViewById(R.id.todopriority);
            cd = itemView.findViewById(R.id.rootcard);
        }
    }
}

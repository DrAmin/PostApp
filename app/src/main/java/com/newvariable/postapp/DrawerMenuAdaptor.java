package com.newvariable.postapp;

import android.graphics.drawable.Icon;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by deepa on 18/02/2016.
 */
public class DrawerMenuAdaptor extends RecyclerView.Adapter<DrawerMenuAdaptor.DrawerViewHolder> {
    List<Integer> icon;
    List<String> data;
    public DrawerMenuAdaptor(List<Integer> icon,List<String> data){
        Log.i("------------------","DrawerMenuAdaptor");
        this.icon=icon;
        this.data=data;
    }
    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_list,parent,false);
        DrawerViewHolder holder=new DrawerViewHolder(view);
        Log.i("+++++++++","onCreateViewHolder");
        return holder;
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        int ic=icon.get(position);
        String list_data=data.get(position);
        Log.i("myyy",list_data);
        holder.title_icon.setImageResource(ic);
        holder.title.setText(list_data);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DrawerViewHolder extends RecyclerView.ViewHolder {
        ImageView title_icon;
        TextView title;
        public DrawerViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_menu_title);
            title_icon=(ImageView)itemView.findViewById(R.id.iv_drawer_menu_icon);
        }
    }
}

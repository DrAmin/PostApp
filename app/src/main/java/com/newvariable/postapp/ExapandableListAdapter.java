package com.newvariable.postapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by deepa on 02/03/2016.
 */
public class ExapandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> group_data;
    private List<Integer> group_icon;
    private HashMap<String,List<String>> child_data;
    List<String> child=new ArrayList<String>();
    public ExapandableListAdapter(Context context,List<String> group_data,List<Integer> group_icon,HashMap<String,List<String>> child_data){
        this.context=context;
        this.group_data=group_data;
        this.group_icon=group_icon;
        this.child_data=child_data;
    }

    @Override
    public int getGroupCount() {
        return group_data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child_data.get(group_data.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group_data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_data.get(group_data.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group_title=(String)getGroup(groupPosition);
        CheckedTextView title=null;
        final ImageView icon,down;
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.row_drawer_group_list,null);
        }
        title=(CheckedTextView)convertView.findViewById(R.id.tv_group_title);
        icon=(ImageView)convertView.findViewById(R.id.iv_drawer_group_icon);
        down=(ImageView)convertView.findViewById(R.id.iv_side_down_icon);

        title.setText(group_data.get(groupPosition));
        title.setChecked(isExpanded);
        icon.setImageResource(group_icon.get(groupPosition));
        int count=getChildrenCount(groupPosition);
        Log.i("group"+groupPosition,"=>child"+count);
        if(count==0){
            down.setVisibility(View.GONE);
        }else{
            down.setImageResource(R.drawable.down);
        }



        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String child_title=(String)getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.row_drawer_child_list,null);
        }
        TextView c_title=(TextView)convertView.findViewById(R.id.tv_child_menu_title);
        c_title.setText(child_title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

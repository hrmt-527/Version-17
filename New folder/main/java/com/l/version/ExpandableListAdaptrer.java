package com.l.version;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by love_y on 17-07-2017.
 */

public class ExpandableListAdaptrer extends BaseExpandableListAdapter {
    private Context context;
    private List<String> list;

    private HashMap<String,List<String>> listHashMap;

    public ExpandableListAdaptrer(Context context, List<String> list, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.list = list;
        this.listHashMap = listHashMap;
    }

    //number of groups
    @Override
    public int getGroupCount() {
return  list.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(list.get(groupPosition)).get(childPosition) ;
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
      String headerTitle  = (String)getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater inflater  = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group,null);
        }
        TextView listheader = (TextView)convertView.findViewById(R.id.listHeader);
        listheader.setTypeface(null, Typeface.BOLD);
        listheader.setText(headerTitle);
                return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childText  = (String)getChild(groupPosition,childPosition);
        if(convertView == null)
        {
            LayoutInflater inflater  = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,null);
        }
        TextView txtListChild = (TextView)convertView.findViewById(R.id.listItem);
        //txtListChild.setTypeface(null, Typeface.BOLD);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

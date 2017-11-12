package com.cseunited.alumni.cseunited;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Client on 11/11/2017
 */

public class PlacementExpandableListAdapter extends BaseExpandableListAdapter {

    private List<String> batches;
    private Map<String, List<String>> cnames;
    private Map<String, Map<String, List<String>>> details;
    private Context context;

    PlacementExpandableListAdapter(Context context, List<String> batches, Map<String, List<String>> cnames, Map<String, Map<String, List<String>>> details) {
        this.context = context;
        this.batches = batches;
        this.cnames = cnames;
        this.details = details;
    }

    @Override
    public int getGroupCount() {
        return batches.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return details.get(batches.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return batches.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Pair<String, List<String>> cn;
        String company = cnames.get(batches.get(groupPosition)).get(childPosition);
        List<String> names = details.get(batches.get(groupPosition)).get(company);
        cn = new Pair<>(company, names);
        return cn;
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
        String batch = (String) getGroup(groupPosition);
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.placement_expandable_list_group,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.batch);
        textView.setText(batch);
        ImageView indicator = (ImageView) convertView.findViewById(R.id.placement_group_indicator);
        if(isExpanded)
            indicator.setImageResource(R.drawable.ic_arrow_drop_up_24dp);
        else
            indicator.setImageResource(R.drawable.ic_arrow_drop_down_24dp);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       final Pair<String, List<String>> company = (Pair<String, List<String>>) getChild(groupPosition,childPosition);
       if(convertView == null) {
           LayoutInflater layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView=layoutInflater.inflate(R.layout.placement_expandable_list_child,null);
       }
       TextView cname = (TextView) convertView.findViewById(R.id.company_name);
       cname.setText(company.first);
       cname.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, company.second);
               AlertDialog.Builder builder = new AlertDialog.Builder(context);
               builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
               AlertDialog dialog = builder.create();
               dialog.show();
           }
       });
       return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

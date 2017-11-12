package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by suyash on 10/31/2017
 */

public class FacultyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Pair<String, String>> names;
    private Map<String, String> images;
    private Map<String, Pair<List<String>, List<String>>> details;

    FacultyExpandableListAdapter(Context context, Map<String, String> images, List<Pair<String, String>> names, Map<String, Pair<List<String>, List<String>>> details){
        this.context = context;
        this.images = images;
        this.names = names;
        this.details = details;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return details.get(names.get(groupPosition).first);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Pair<List<String>, List<String>> details = (Pair<List<String>, List<String>>) getChild(groupPosition, childPosition);
        List<String> achievements = details.first;
        List<String> publications = details.second;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.faculty_expandable_list_child, null);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.faculty_image);
        TextView achievements_view = (TextView) convertView.findViewById(R.id.faculty_achievement_view);
        TextView publications_view = (TextView) convertView.findViewById(R.id.faculty_publication_view);

        StringBuilder br1 = new StringBuilder();
        for(int i=0;i<achievements.size();i++){
            br1.append(achievements.get(i)).append("\n");
        }

        achievements_view.setText(br1);

        StringBuilder br2 = new StringBuilder();
        for(int i=0;i<publications.size();i++){
            br2.append(publications.get(i)).append("\n");
        }

        publications_view.setText(br2);

        Picasso.with(context).load(images.get(names.get(groupPosition).first)).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(image);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public int getGroupCount() {
        return names.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return names.get(groupPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Pair<String, String> name_desig = (Pair<String, String>) getGroup(groupPosition);
        String name = name_desig.first;
        String desig = name_desig.second;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.faculty_expandable_list_group, null);
        }

        TextView name_view = (TextView) convertView.findViewById(R.id.faculty_name);
        TextView desig_view = (TextView) convertView.findViewById(R.id.faculty_desig);
        ImageView indicator = (ImageView) convertView.findViewById(R.id.faculty_group_indicator);

        if(isExpanded)
            indicator.setImageResource(R.drawable.ic_arrow_drop_up_24dp);
        else
            indicator.setImageResource(R.drawable.ic_arrow_drop_down_24dp);

        name_view.setText(name);
        desig_view.setText(desig);

        return convertView;
    }
}

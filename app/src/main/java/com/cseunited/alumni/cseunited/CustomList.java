package com.cseunited.alumni.cseunited;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sachin on 10/30/2017.
 */

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] teacher;
    private final String[] teacher_desig;
    private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] teacher, String[] teacher_desig, Integer[] imageId) {
        super(context, R.layout.list_single, teacher);
        this.context = context;
        this.teacher = teacher;
        this.imageId = imageId;
        this.teacher_desig=teacher_desig;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.TeacherName);
        TextView txtTitle1 = (TextView) rowView.findViewById(R.id.TeacherDesignation);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(teacher[position]);
        txtTitle1.setText(teacher_desig[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}

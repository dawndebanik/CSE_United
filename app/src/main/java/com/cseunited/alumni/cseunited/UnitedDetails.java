package com.cseunited.alumni.cseunited;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitedDetails extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("CSE United 2015");
        listDataHeader.add("CSE United and Alumni Meet 2014");
        listDataHeader.add("CSE Alumni Meet");
        listDataHeader.add("CSE United 2012");
        listDataHeader.add("CSE United 2011");

        // Adding child data
        List<String> u15 = new ArrayList<String>();
        u15.add("In the 5th year, CSE UNITED soared to an entirely new height. The event took place at the EZCC (Eastern Zonal Cultural Centre) Hall. For the first time, in five years, the event took place outside the college premises. Following our traditions, the hall was decorated, keeping in mind the significance of the celebration of the 5th year. Starting from the inauguration to the end, all the events had a cultural as well as a western touch to it. In addition, the Mr. and Miss CSE event was introduced this year. ");

        List<String> u14 = new ArrayList<String>();
        u14.add("CSE United is our way of welcoming the first years to our family. What better than combine it with an alumni meet as, after all, in the words of our beloved CKB Sir, an institution is not known by its faculty but its alumni.");

        List<String> u13 = new ArrayList<String>();
        u13.add("This was the first alumni meet organised by the CSE Department of Techno India. The meet proved to be a success, and we had quite a few alumni members joining us to guide and inspire the first years. A Q&A session on various aspects of corporate life and academics was followed by fun events organised by the alumni members themselves. Students of the department also presented their take on the alumni website and the group led by Soumajit Biswas was declared the winner. The event came to an end with a light-hearted game of tug-of-war, with students of the first, second, third and fourth years as well as one consisting of members of the Alumni competing for the title. ");

        List<String> u12 = new ArrayList<String>();
        u12.add("By 2012, CSE United had become a day everyone looks forward to. The second ever CSE Unted was held in late 2012 and consisted of the usual fare – songs, dances, games, etc. The events of the day included:" + "\n" +
                "Dance Performances by Russa and Smita, Deepika, Prema, Saurav Kothari" + "\n" +
                "A group song by Jyotirmoy and his group" + "\n" +
                "A recitation by Sayan, a fresher at the time" + "\n" +
                "A recitation by Ritesh" + "\n" +
                "A Song by Joydeep sir" + "\n" +
                "A group song by Meghdut and his Group" + "\n" +
                "Songs by CKB sir and Poulami ma'am");

        List<String> u11 = new ArrayList<String>();
        u11.add(" The first ever CSE United was deviced as a cultural program to welcome freshers to the Computer Science and Engineering Department. The events of the day included:" + "\n" +
                "A recitation by Ritesh" + "\n" +
                "A song by Souvik and Subham" + "\n" +
                "A group song by Meghdut" + "\n" +
                "Dance performances by Saurav Kothari, Kavita and Deepika" + "\n" +
                "A game of musical chair by the teachers" + "\n" +
                "Interaction with first years" + "\n" +
                "Songs by CKB sir and Poulami ma'am");

        listDataChild.put(listDataHeader.get(0), u15); // Header, Child data
        listDataChild.put(listDataHeader.get(1), u14);
        listDataChild.put(listDataHeader.get(2), u13);
        listDataChild.put(listDataHeader.get(3), u12);
        listDataChild.put(listDataHeader.get(4), u11);
    }
}
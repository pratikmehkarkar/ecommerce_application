package com.jayesh.jayesh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Shubham on 11/3/2017.
 */

public class adapter_categorylist extends ArrayAdapter<category>
{
    //the hero list that will be displayed
    private List<category> catList;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public adapter_categorylist(List<category> catList, Context mCtx) {
        super(mCtx, R.layout.category_listview, catList);
        this.catList = catList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.category_listview, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.tvcategory);


        //Getting the hero for the specified position
        category categories = catList.get(position);

        //setting hero values to textviews
        textViewName.setText(categories.getName());


        //returning the listitem
        return listViewItem;
    }


}

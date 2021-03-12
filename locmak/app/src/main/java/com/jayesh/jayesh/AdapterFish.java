package com.jayesh.jayesh;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jayesh.jayesh.List_Product.Productlist_fragment;
import com.jayesh.jayesh.Product_Details.productdetail;

import java.util.Collections;
import java.util.List;


public class AdapterFish extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataFish> data= Collections.emptyList();
    DataFish current;
    String test;

    // create constructor to initialize context and data sent from MainActivity
    public AdapterFish(Context context, List<DataFish> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=inflater.inflate(R.layout.container_fish, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
       current=data.get(position);
        myHolder.textFishName.setText(current.fishName);
        myHolder.textSize.setText("Category: " + current.sizeName);
        myHolder.textType.setText("About Product: " + current.catName);
        myHolder.textPrice.setText("Rs. " + current.price);
        myHolder.txtid.setText(current.pid);


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textFishName;
        TextView textSize;
        TextView textType;
        TextView textPrice;
        TextView txtid;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textFishName= (TextView) itemView.findViewById(R.id.textFishName);
            textSize = (TextView) itemView.findViewById(R.id.textSize);
            textType = (TextView) itemView.findViewById(R.id.textType);
            textPrice = (TextView) itemView.findViewById(R.id.textPrice);
            txtid = (TextView) itemView.findViewById(R.id.textid);
            textPrice.setVisibility(View.INVISIBLE);
            txtid.setVisibility(View.INVISIBLE);
           itemView.setOnClickListener(this);
        }

        // Click event for all items
      @Override
        public void onClick(View v)
        {
            Productlist_fragment.productid = txtid.getText().toString();
            Productlist_fragment.pdesc = textType.getText().toString();
            Productlist_fragment.pprice = textPrice.getText().toString();
            Productlist_fragment.itemname = textFishName.getText().toString();
            AppCompatActivity activity = (AppCompatActivity) context;
            FragmentManager fm = activity.getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.mainframe,new productdetail(),"pdetailon").addToBackStack("tag");
            ft.commit();
            //Toast.makeText(context, "You clicked an item"+dete, Toast.LENGTH_SHORT).show();
        }

    }

}

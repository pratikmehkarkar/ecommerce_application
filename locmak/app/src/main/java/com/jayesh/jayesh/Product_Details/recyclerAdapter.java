package com.jayesh.jayesh.Product_Details;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jayesh.jayesh.List_Product.RecyclerViewClickListener;
import com.jayesh.jayesh.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Shubham on 11/24/2017.
 */

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyHolder> {

    List<product> list;
    ImageLoader imageLoader;
    FragmentManager fManager;
    FragmentTransaction tx;
    String key;

    public recyclerAdapter(List<product> list, ImageLoader imageLoader) {
        this.list = list;
        this.imageLoader = imageLoader;

    }

    @Override
    public recyclerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailcard, parent, false);
        recyclerAdapter.MyHolder myHolder = new recyclerAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(recyclerAdapter.MyHolder holder, final int position) {
        product product = list.get(position);

        String image1 = product.getImageurl1();
        imageLoader.displayImage(image1, holder.image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder  {

        ImageView image;

        public MyHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageview);

        }

    }


}

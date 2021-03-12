package com.jayesh.jayesh.cart;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jayesh.jayesh.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Shubham on 11/26/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyHolder>{

   public Cartdata cartdata;
    List<Cartdata> list1;
    ImageLoader imageLoader;
    FragmentManager fManager;
    FragmentTransaction tx;
    private RecyclerViewClickListener mListener;
    Context c;
    private CartFragment cf = new CartFragment();
    public RecycleAdapter(List<Cartdata> list, ImageLoader imageLoader,RecyclerViewClickListener listener) {
        this.list1 = list;
        this.imageLoader = imageLoader;
        mListener=listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlist, parent, false);
        MyHolder myHolder = new MyHolder(view,mListener);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        cartdata = list1.get(position);
        holder.name.setText(cartdata.getProductname());
        holder.id.setText(cartdata.getProductid());
        String cartid1 =cartdata.getProductid().toString();
        String image1 = cartdata.getImageurl();
        imageLoader.displayImage(image1, holder.image);
        holder.price.setText("INR " + cartdata.getPrice());

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.removeitem(cartdata.getProductid().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class MyHolder extends RecyclerView.ViewHolder  {

        TextView name, id, price;
        ImageView image;
        Button remove,buynow;
        private RecyclerViewClickListener mListener;
        public MyHolder(View itemView,RecyclerViewClickListener listener) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.productimage);
            name = (TextView) itemView.findViewById(R.id.tvproduct_name);
            price = (TextView) itemView.findViewById(R.id.tv_price);
            id = (TextView) itemView.findViewById(R.id.tv_id);
            remove = (Button)itemView.findViewById(R.id.btnremove);
            buynow= (Button)itemView.findViewById(R.id.btnbuynow);
            mListener=listener;

        }
    }
}


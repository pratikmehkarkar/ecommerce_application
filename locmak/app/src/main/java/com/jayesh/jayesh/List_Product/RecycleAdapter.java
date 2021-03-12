package com.jayesh.jayesh.List_Product;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jayesh.jayesh.CategoryFragment;
import com.jayesh.jayesh.Product_Details.productdetail;
import com.jayesh.jayesh.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Shubham on 11/19/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyHolder> {

    List<Product> list1;
    ImageLoader imageLoader;
    FragmentManager fManager;
    FragmentTransaction tx;
    private RecyclerViewClickListener mListener;
    private Context context;
    int rowlayout;
    Productlist_fragment pl;
    public String itemname,pid1,price1,desc;

    public RecycleAdapter(List<Product> list, ImageLoader imageLoader,Productlist_fragment pl) {
        this.list1 = list;
        this.imageLoader = imageLoader;
        this.pl=pl;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        Product product = list1.get(position);
        holder.name.setText(product.getProductname());
        String image1 = product.getImageurl();
        imageLoader.displayImage(image1, holder.image);
        holder.price.setText("INR " + product.getPrice());
        holder.tvpid.setText(product.getProductid());
        holder.tvdesc.setText(product.getDesc());
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class MyHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        TextView name,price,tvpid,tvdesc;
        ImageView image;
        Context context;

        private RecyclerViewClickListener mListener;
        public MyHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productimage);
            name = itemView.findViewById(R.id.name);
            //pid= (TextView) itemView.findViewById(R.id.tvproductid);
            price = itemView.findViewById(R.id.price);
            tvpid = itemView.findViewById(R.id.tvpid);
            tvdesc = itemView.findViewById(R.id.tvdesc);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {

            itemname=name.getText().toString();
            price1=price.getText().toString();
            pid1=tvpid.getText().toString();
            desc=tvdesc.getText().toString();
//            pid1=productid.getText().toString();
          // pl.getdetail();
            if(pl!=null)
            {
                //Toast.makeText(view.getContext(),""+getAdapterPosition()+itemname+price1+pid1+desc,Toast.LENGTH_LONG).show();
                pl.getdetail(pid1,price1,itemname,desc);
            }

            //if(mListener!=null) mListener.onClick(view,getAdapterPosition());



        }

    }
}

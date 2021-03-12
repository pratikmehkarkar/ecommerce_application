package com.jayesh.jayesh.offer;

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
 * Created by Gopss on 17-Dec-17.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferHolder>{

    List<OfferData> list1;
    ImageLoader imageLoader;
    FragmentManager fManager;
    FragmentTransaction tx;
    Offer_Fragment of;
    private ProductClickListener mListener;
    Context c;
    String pid,price,name,offerprice1;
    public OfferAdapter(List<OfferData> list, ImageLoader imageLoader,Offer_Fragment of) {
        this.list1 = list;
        this.imageLoader = imageLoader;
        // mListener=listener;
        this.of=of;
    }

    @Override
    public OfferHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offerlist_fragment, parent, false);
        OfferHolder myHolder = new OfferHolder(view,mListener);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(OfferHolder holder, final int position) {
        OfferData offerdata = list1.get(position);
        holder.pname.setText("Name : "+offerdata.getPname());
        holder.originalprice.setText("Original Price : "+offerdata.getOriginalprice());
        holder.offerprice.setText("Offer Price : "+offerdata.getOfferprice());
        holder.productid.setText("Productid : "+offerdata.getProductid());
        String image1 = offerdata.getImageurl();
        imageLoader.displayImage(image1, holder.pimage);

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class OfferHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView pname,originalprice,offerprice,productid;
        ImageView pimage;
        Button remove,buynow;
        private ProductClickListener mListener;
        public OfferHolder(View itemView,ProductClickListener listener) {
            super(itemView);
            pimage = (ImageView) itemView.findViewById(R.id.productiview);
            pname = (TextView) itemView.findViewById(R.id.pname);
            originalprice = (TextView) itemView.findViewById(R.id.originalprice);
            offerprice = (TextView) itemView.findViewById(R.id.offerprice);
            productid = (TextView) itemView.findViewById(R.id.productid);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            if(of!=null)
            {
                pid=productid.getText().toString();
                price=originalprice.getText().toString();
                name=pname.getText().toString();
                offerprice1=offerprice.getText().toString();

                Toast.makeText(view.getContext(),""+getAdapterPosition()+pid+price+name+offerprice, Toast.LENGTH_LONG).show();
                of.getdata(pid,price,offerprice1,name);
            }
        }
    }
}
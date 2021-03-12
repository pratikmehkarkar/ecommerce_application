package com.jayesh.jayesh.offer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jayesh.jayesh.Home;
import com.jayesh.jayesh.Product_Details.productdetail;
import com.jayesh.jayesh.R;
import com.jayesh.jayesh.cart.RecyclerViewClickListener;
import com.jayesh.jayesh.cart.Verticalspacing;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gopss on 17-Dec-17.
 */

public class Offer_Fragment extends android.app.Fragment  implements ProductClickListener {

    RecyclerView offerrview;
    List<OfferData> offerlist;
    FragmentManager fm;
    FragmentTransaction fx;
    String product_subcat = "T-shirts & Polos";
    String product_category ,cat;
    public static String pid,price,name,offerprice;
    public  static String classname="offer";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.offer_fragment,container,false);
        offerrview=(RecyclerView)v.findViewById(R.id.offerrview);
        offerrview.setNestedScrollingEnabled(false);
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(getActivity()).build();
        ImageLoader.getInstance().init(config);
        offerlist = new ArrayList<>();

        if (Home.men1 == "men")
        {
            cat = "11";
        }
        if (Home.women1 == "women")
        {
            cat = "12";
        }
        if (Home.kids1 == "kids")
        {
            cat = "13";
        }


        Toast.makeText(getActivity(),cat,Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://locmak.com/locmak/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Offer_Api service = retrofit.create(Offer_Api.class);
        Call<List<OfferData>> call=   service.getofferlist(cat);
        call.enqueue(new Callback<List<OfferData>>() {
            @Override
            public void onResponse(Call<List<OfferData>> call, Response<List<OfferData>> response) {

                List<OfferData> list = response.body();
                OfferData offerData = null;
                for (int i =0 ;i<list.size();i++){
                    offerData = new OfferData();
                    String pname = list.get(i).getPname();
                    String productid  = list.get(i).getProductid();
                    String imageurl = list.get(i).getImageurl();
                    String originalprice = list.get(i).getOriginalprice();
                    String offerprice = list.get(i).getOfferprice();

                    offerData.setOfferprice(offerprice);
                    offerData.setProductid(productid);
                    offerData.setOriginalprice(originalprice);
                    offerData.setPname(pname);
                    offerData.setImageurl(imageurl);

                    offerlist.add(offerData);
                }

              /*  RecyclerViewClickListener listener=(view, position)->
                {
                    Toast.makeText(getActivity(),position, Toast.LENGTH_LONG).show();
                };*/
                final OfferAdapter recyclerAdapter = new OfferAdapter(offerlist,ImageLoader.getInstance(),Offer_Fragment.this);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(getActivity(),1);
                // RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                offerrview.setLayoutManager(recyce);
                offerrview.setItemAnimator( new DefaultItemAnimator());
                offerrview.addItemDecoration(new Verticalspacing(8));
                offerrview.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<OfferData>> call, Throwable t) {

                Toast.makeText(getActivity(),"error",Toast.LENGTH_LONG).show();

            }
        });


        return v;
    }

    @Override
    public void getdata(String pid, String price, String name, String offerprice) {

        this.pid=pid;
        this.price=price;
        this.name=name;
        this.offerprice=offerprice;
        classname="offer";
        FragmentManager  fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.mainframe,new productdetail()).addToBackStack("tag");
        ft.commit();
    }
}

package com.jayesh.jayesh.cart;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayesh.jayesh.CategoryFragment;
import com.jayesh.jayesh.List_Product.*;
import com.jayesh.jayesh.MainActivity;
import com.jayesh.jayesh.Product_Details.productdetail;
import com.jayesh.jayesh.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.jayesh.jayesh.helper.SQLiteHandler;
import com.jayesh.jayesh.helper.SessionManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shubham on 10/2/2017.
 */

public class CartFragment extends Fragment implements RecyclerViewClickListener{

    RecyclerView recyclerView;
    List<Cartdata> cartlist;
    List<CartDataInsert> cartlist1;
    FragmentManager fm;
    FragmentTransaction fx;
    String contact,sucess;
    private Context context=null;
    public static   int cartid;
    private SQLiteHandler db;
    private SessionManager session;
    public static String proid=Productlist_fragment.productid;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.cartfragment,container,false);
        recyclerView = (RecyclerView)v.findViewById(R.id.cartrecycle);
        recyclerView.setNestedScrollingEnabled(false);
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(getActivity()).build();
        ImageLoader.getInstance().init(config);
        cartlist = new ArrayList<>();
        cartlist1 = new ArrayList<>();
        context=getActivity();

        db = new SQLiteHandler(getActivity());
        session = new SessionManager(getActivity());
        if(!session.isLoggedIn())
        {
            //logoutUser();
        }
        HashMap<String,String> user = db.getUserDetails();

        contact = user.get("mobile");
        //String email = user.get("email");

       // contact=String.valueOf(name);




            Retrofit retrofit1 = new Retrofit.Builder()
                    .baseUrl("http://locmak.com/locmak/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api_cart service1 = retrofit1.create(api_cart.class);

            Call<List<Cartdata>> call2=   service1.getcartlist(contact);
            call2.enqueue(new Callback<List<Cartdata>>() {
                @Override
                public void onResponse(Call<List<Cartdata>> call, Response<List<Cartdata>> response) {

                    List<Cartdata> list = response.body();
                    Cartdata cartdata = null;
                    for (int i =0 ;i<list.size();i++){
                        cartdata = new Cartdata();
                        String name = list.get(i).getProductname();
                        String productid  = list.get(i).getProductid();
                        String image = list.get(i).getImageurl();
                        String price = list.get(i).getPrice();
                        cartid = Integer.parseInt(list.get(i).getCartid());
                        cartdata.setPrice(price);
                        cartdata.setProductid(productid);
                        cartdata.setProductname(name);
                        cartdata.setImageurl(image);
                        cartdata.setCartid(String.valueOf(cartid));
                        cartlist.add(cartdata);
                    }

                    RecyclerViewClickListener listener=(cartid)->
                    {
                      //  Toast.makeText(getActivity(),"listener"+cartid,Toast.LENGTH_LONG).show();
                    };
                    final RecycleAdapter recyclerAdapter = new RecycleAdapter(cartlist,ImageLoader.getInstance(),listener);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(getActivity(),1);
                    // RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(recyce);
                    recyclerView.setItemAnimator( new DefaultItemAnimator());
                    recyclerView.addItemDecoration(new Verticalspacing(16));
                    recyclerView.setAdapter(recyclerAdapter);
                }

                @Override
                public void onFailure(Call<List<Cartdata>> call2, Throwable t) {

                    Toast.makeText(getActivity(),"error",Toast.LENGTH_LONG).show();

                }
            });



        return v;



    }


    @Override
    public void removeitem(String cartid) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://locmak.com/locmak/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            String cid=cartid;


            // if (CartFragment.this.isVisible() && cid != null & context != null)
            Toast.makeText(getActivity(), "remvieittem method"+cid, Toast.LENGTH_LONG).show();
            api_cart service = retrofit.create(api_cart.class);
            Call<ResponseBody> call=   service.removeitem(cartid);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                    Toast.makeText(context, "product remove from cart"+response, Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    Toast.makeText(context, "error "+t, Toast.LENGTH_LONG).show();

                }
            });


    }
}



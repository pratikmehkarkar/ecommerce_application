package com.jayesh.jayesh.Product_Details;


import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayesh.jayesh.order.OrderFragment;
import com.jayesh.jayesh.List_Product.Productlist_fragment;
import com.jayesh.jayesh.R;
import com.jayesh.jayesh.cart.CartDataInsert;
import com.jayesh.jayesh.cart.api_cart;
import com.jayesh.jayesh.helper.SQLiteHandler;
import com.jayesh.jayesh.helper.SessionManager;
import com.nostra13.universalimageloader.core.ImageLoader;
        import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

        import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

        import static com.jayesh.jayesh.List_Product.Productlist_fragment.itemname;
        import static com.jayesh.jayesh.List_Product.Productlist_fragment.pdesc;
        import static com.jayesh.jayesh.List_Product.Productlist_fragment.pprice;
        import static com.jayesh.jayesh.List_Product.Productlist_fragment.productid;
import static com.jayesh.jayesh.cart.CartFragment.proid;


public class productdetail extends android.app.Fragment {
    RecyclerView rview;
    String key;
FragmentManager fm=getFragmentManager();
FragmentTransaction fx;
    List<product> listing,listing1;
   /* public  static String pproductid = Productlist_fragment.productid;
    public  static String pprice = Productlist_fragment.pprice;
    public  static String pdesc= Productlist_fragment.pdesc;
    public  static String pname = Productlist_fragment.itemname;*/
    ImageButton btnaddtocart ,btnbuynow;
    private SQLiteHandler db;
    private SessionManager session;
    String contact;
    TextView productname,pid,productprice,desc;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.productdetail_fragment,container,false);
        rview=(RecyclerView) v.findViewById(R.id.productimagerview);
        productname=(TextView)v.findViewById(R.id.tvProductname);
        productprice=(TextView)v.findViewById(R.id.tvprice);
        pid=(TextView)v.findViewById(R.id.tvproductid) ;
        desc=(TextView)v.findViewById(R.id.desc);
        btnaddtocart=(ImageButton)v.findViewById(R.id.btnaddtocart);
        btnbuynow=(ImageButton)v.findViewById(R.id.btnbuynow);

        db = new SQLiteHandler(getActivity());
        session = new SessionManager(getActivity());
        if(!session.isLoggedIn())
        {
            //logoutUser();
        }
        HashMap<String,String> user = db.getUserDetails();

        contact = user.get("mobile");

        productname.setText(itemname);
        productprice.setText(pprice);
        desc.setText("Description:  "+pdesc);
        pid.setText(productid);
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(getActivity()).build();
        ImageLoader.getInstance().init(config);
        listing = new ArrayList<>();
       // final TextView productid1=(TextView)v.findViewById(R.id.tvproductid);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://locmak.com/locmak/locmakimage/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiservice service = retrofit.create(apiservice.class);
        Call<List<product>> call=   service.getProductdetails(productid);
        call.enqueue(new Callback<List<product>>() {
            @Override
            public void onResponse(Call<List<product>> call, Response<List<product>> response) {

                List<product> list = response.body();
                product product = null;
                for (int i = 0; i < list.size(); i++) {
                    product = new product();
                    String img1 = list.get(i).getImageurl1();
                    product.setImageurl1(img1);
                    listing.add(product);
                }


                final recyclerAdapter recyclerAdapter = new recyclerAdapter(listing,ImageLoader.getInstance());

                final LinearLayoutManager lManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                rview.setLayoutManager(lManager);
                rview.setAdapter(recyclerAdapter);



            }

            @Override
            public void onFailure(Call<List<product>> call, Throwable t) {

            }
        } );

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OkHttpClient client = new OkHttpClient();
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://locmak.com/locmak/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                api_cart service = retrofit.create(api_cart.class);

                CartDataInsert cartDataInsert=new CartDataInsert();
                cartDataInsert.setContact(contact);
                cartDataInsert.setProducid(proid);

                Call<CartDataInsert> call1=   service.insertCartData(contact,proid);
                call1.enqueue(new Callback<CartDataInsert>() {
                    @Override
                    public void onResponse(Call<CartDataInsert> call, Response<CartDataInsert> response) {

                        Toast.makeText(getActivity(), "sucessfully added to cart"+cartDataInsert.getContact()+cartDataInsert.getProducid(), Toast.LENGTH_LONG).show();
                        //sucess="1";
                    }

                    @Override
                    public void onFailure(Call<CartDataInsert> call1, Throwable t) {


                        Log.i("Hello",""+t);
                        Toast.makeText(getActivity(), "available in cart"+t, Toast.LENGTH_LONG).show();

                    }

                });

            }
        });

        btnbuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 fm=getFragmentManager();
                 fx=fm.beginTransaction();
                 fx.replace(R.id.mainframe,new OrderFragment()).addToBackStack("tag");
                 fx.commit();
            }
        });



        return v;

    }


}

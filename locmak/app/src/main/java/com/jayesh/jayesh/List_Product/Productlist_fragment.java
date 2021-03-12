package com.jayesh.jayesh.List_Product;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jayesh.jayesh.CategoryFragment;
import com.jayesh.jayesh.MainActivity;
import com.jayesh.jayesh.Product_Details.productdetail;
import com.jayesh.jayesh.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Productlist_fragment extends Fragment implements RecyclerViewClickListener
{
    RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    List<Product> listing;
    Integer maincategory =MainActivity.mainCategory;
    String subcategory =CategoryFragment.subcategory;
    FragmentManager fm;
    FragmentTransaction fx;
    Productlist_fragment pl;
    public static String productid,itemname,pprice,pdesc;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.productlist_fragment,container,false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycle);
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(getActivity()).build();
        ImageLoader.getInstance().init(config);
        listing = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://locmak.com/locmak/locmakimage/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api_service service = retrofit.create(Api_service.class);
        Call<List<Product>> call=   service.getproductlist(maincategory,subcategory);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                List<Product> list = response.body();
                Product product = null;
                for (int i =0 ;i<list.size();i++){
                    product = new Product();
                    String name = list.get(i).getProductname();
                    String proid  = list.get(i).getProductid();
                    String image = list.get(i).getImageurl();
                    String price = list.get(i).getPrice();
                    String desc = list.get(i).getDesc();

                    product.setDesc(desc);
                    product.setProductid(proid);
                    product.setPrice(price);
                    product.setProductname(name);
                    product.setImageurl(image);
                    listing.add(product);
                }


                final RecycleAdapter recyclerAdapter = new RecycleAdapter(listing,ImageLoader.getInstance(),Productlist_fragment.this);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(getActivity(),2);
                // RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                recyclerView.addItemDecoration(new GridSpacingdecoration(2, dpToPx(10), true));
                recyclerView.setLayoutManager(recyce);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Toast.makeText(getActivity(),"error",Toast.LENGTH_LONG).show();

            }
        });

        return v;
    }

    @Override
    public void getdetail(String pid,String price,String name ,String desc) {

        productid=pid;
        pprice=price;
        itemname=name;
        pdesc=desc;
        FragmentManager  fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.mainframe,new productdetail(),"pdetail").addToBackStack("tag");
        ft.commit();
    }


    public class GridSpacingdecoration extends RecyclerView.ItemDecoration {

        private int span;
        private int space;
        private boolean include;

        public GridSpacingdecoration(int span, int space, boolean include) {
            this.span = span;
            this.space = space;
            this.include = include;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % span;

            if (include) {
                outRect.left = space - column * space / span;
                outRect.right = (column + 1) * space / span;

                if (position < span) {
                    outRect.top = space;
                }
                outRect.bottom = space;
            } else {
                outRect.left = column * space / span;
                outRect.right = space - (column + 1) * space / span;
                if (position >= span) {
                    outRect.top = space;
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}



package com.jayesh.jayesh;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.jayesh.jayesh.helper.SQLiteHandler;
import com.jayesh.jayesh.helper.SessionManager;
import com.jayesh.jayesh.offer.Offer_Fragment;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Home extends Fragment implements View.OnClickListener {
    TextView tname;
    private SQLiteHandler db;
    private SessionManager session;
    ImageButton men,women,kids,elect;
    FragmentManager fm;
    public  static  String men1,women1,kids1;
    ImageView banner,menimg,womenimg;
    FragmentTransaction fx;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        tname = (TextView)v.findViewById(R.id.txtname);
        men= (ImageButton)v.findViewById(R.id.imen);
        women = (ImageButton)v.findViewById(R.id.iwomen);
        kids = (ImageButton)v.findViewById(R.id.ikid);
        elect = (ImageButton)v.findViewById(R.id.ielect);
        banner = (ImageView)v.findViewById(R.id.bnr);
        menimg = (ImageView)v.findViewById(R.id.menoff);
        womenimg = (ImageView)v.findViewById(R.id.womenoff);
        Picasso.with(getActivity()).load("http://www.locmak.com/locmakimages/mainbanner.jpg").into(banner);
        Picasso.with(getActivity()).load("http://www.locmak.com/locmakimages/men.jpg").into(menimg);
        Picasso.with(getActivity()).load("http://www.locmak.com/locmakimages/women.jpg").into(womenimg);
        men.setOnClickListener(this);
        women.setOnClickListener(this);
        kids.setOnClickListener(this);
        elect.setOnClickListener(this);
        menimg.setOnClickListener(this);
        womenimg.setOnClickListener(this);
        db = new SQLiteHandler(getActivity());
        session = new SessionManager(getActivity());
        if(!session.isLoggedIn())
        {
            //logoutUser();
        }
        HashMap<String,String> user = db.getUserDetails();

        String name = user.get("name");
        //String email = user.get("email");

        tname.setText("Hello, "+name);
        return v;
    }

    @Override
    public void onClick(View v)
    {
        if(men == v)
        {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            MainActivity.JSON_URL = "http://locmak.com/locmak/cat_men.php";
            MainActivity.mainCategory =11;
            fx.replace(R.id.mainframe,new CategoryFragment(),"catf").addToBackStack("tag");
            fx.commit();
        }
        if(women == v)
        {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            MainActivity.JSON_URL = "http://locmak.com/locmak/cat_women.php";
            MainActivity.mainCategory=12;
            fx.replace(R.id.mainframe,new CategoryFragment(),"catf").addToBackStack("tag");
            fx.commit();
        }
        if(kids == v)
        {
            fm = getFragmentManager();
            fx = fm.beginTransaction();
            MainActivity.JSON_URL = "http://locmak.com/locmak/cat_kids.php";
            MainActivity.mainCategory = 13;
            fx.replace(R.id.mainframe, new CategoryFragment(),"catf").addToBackStack("tag");
            fx.commit();
        }
        if(elect == v)
        {

            fm=getFragmentManager();
            fx=fm.beginTransaction();
            MainActivity.JSON_URL = "http://locmak.com/locmak/cat_electronics.php";
            MainActivity.mainCategory=15;
            fx.replace(R.id.mainframe,new CategoryFragment(),"catf").addToBackStack("tag");
            fx.commit();
        }
        if(v == menimg)
        {
            men1="women";
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new Offer_Fragment()).addToBackStack("tag");
            fx.commit();
        }
        if(v == womenimg)
        {
            women1="women";
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new Offer_Fragment()).addToBackStack("tag");
            fx.commit();
        }
    }
}

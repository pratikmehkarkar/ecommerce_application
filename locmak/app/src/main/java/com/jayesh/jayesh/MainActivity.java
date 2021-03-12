package com.jayesh.jayesh;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jayesh.jayesh.List_Product.Productlist_fragment;
import com.jayesh.jayesh.activity.UserAccount;
import com.jayesh.jayesh.cart.CartFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    FragmentManager fm;
    FragmentTransaction fx;
    static String JSON_URL;
    TextView homee;
    Fragment current =null;
    public  static Integer mainCategory;
    static String query;
    //FrameLayout fl;
    SearchView searchView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm=getFragmentManager();
        fx=fm.beginTransaction();
        fx.replace(R.id.mainframe,new Home(),"home");
        fx.commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //fl = (FrameLayout)findViewById(R.id.mainframe);
        //getSupportActionBar().setLogo(R.drawable.lmckn);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        //if(getSupportActionBar() != null)
        //{
          //  getSupportActionBar().setDisplayShowTitleEnabled(false);
        //}
        //toolbar.setTitle("");
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText("LOCMAK");
                tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/BOD_B.TTF");
        tv.setTypeface(tf);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //navigationView.setCheckedItem(R.id.nav_Home);

        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
        homee = (TextView)header.findViewById(R.id.hme);
        homee.setOnClickListener(this);


    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        fm = getFragmentManager();
        current= fm.findFragmentById(R.id.mainframe);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(current.getTag().equals("search"))
        {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new Home(),"home");
            fx.commit();
        }
        else if(current.getTag().equals("cat"))
        {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new Home(),"home");
            fx.commit();
        }
        else if(current.getTag().equals("plist"))
        {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new CategoryFragment(),"cat");
            fx.commit();
        }
        else if(current.getTag().equals("pdetail"))
        {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new Productlist_fragment(),"plist");
            fx.commit();
        }
        else if(current.getTag().equals("user"))
        {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new Home(),"home");
            fx.commit();
        }
        else if(current.getTag().equals("pdetailon"))
        {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new Home(),"home");
            fx.commit();
        }
        else if(current.getTag().equals("home"))
        {
            builder.setMessage("Do you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        else
        {
            builder.setMessage("Do you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        // Get Search item from action bar and Get Search service
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();

        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
            //searchView.setIconified(false);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id=item.getItemId();
        if (id == R.id.action_cart)
        {
            fm = getFragmentManager();
            fx = fm.beginTransaction();
            fx.replace(R.id.mainframe,new CartFragment()).addToBackStack("tag");
            fx.commit();
        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_men) {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            JSON_URL = "http://locmak.com/locmak/cat_men.php";
            mainCategory=11;
            fx.replace(R.id.mainframe,new CategoryFragment(),"cat").addToBackStack("tag");
            fx.commit();

        } else if (id == R.id.nav_women) {

            fm=getFragmentManager();
            fx=fm.beginTransaction();
            JSON_URL = "http://locmak.com/locmak/cat_women.php";
            mainCategory=12;
            fx.replace(R.id.mainframe,new CategoryFragment(),"cat").addToBackStack("tag");
            fx.commit();

        }else if (id == R.id.nav_kids) {

            fm = getFragmentManager();
            fx = fm.beginTransaction();
            JSON_URL = "http://locmak.com/locmak/cat_kids.php";
            mainCategory = 13;
            fx.replace(R.id.mainframe, new CategoryFragment(),"cat").addToBackStack("tag");
            fx.commit();

        }
        else if (id == R.id.nav_Books) {

            fm=getFragmentManager();
            fx=fm.beginTransaction();
            JSON_URL = "http://locmak.com/locmak/cat_book.php";
            mainCategory=16;
            fx.replace(R.id.mainframe,new CategoryFragment(),"cat").addToBackStack("tag");
            fx.commit();

        }
        else if (id == R.id.nav_mobile) {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            JSON_URL = "http://locmak.com/locmak/cat_mobile.php";
            mainCategory=14;
            fx.replace(R.id.mainframe,new CategoryFragment(),"cat").addToBackStack("tag");
            fx.commit();
        }
        else if (id == R.id.nav_electronics) {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            JSON_URL = "http://locmak.com/locmak/cat_electronics.php";
            mainCategory=15;
            fx.replace(R.id.mainframe,new CategoryFragment(),"cat").addToBackStack("tag");
            fx.commit();
        }
        else if (id == R.id.nav_orders) {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new Home());
            fx.commit();

        } else if (id == R.id.nav_account)
        {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new UserAccount(),"user").addToBackStack("tag");
            fx.commit();

        }
        else
        {
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new Home(),"home");
            fx.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            query = intent.getStringExtra(SearchManager.QUERY);

            if (searchView != null) {
               searchView.setIconified(true);
               searchView.setFocusable(false);
               searchView.clearFocus();
            }
            fm=getFragmentManager();
            fx=fm.beginTransaction();
            fx.replace(R.id.mainframe,new SearchFragment(),"search");
            fx.commit();

        }
    }

    @Override
    public void onClick(View v)
    {
        fm=getFragmentManager();
        fx=fm.beginTransaction();
        fx.replace(R.id.mainframe,new Home());
        fx.commit();

    }
}
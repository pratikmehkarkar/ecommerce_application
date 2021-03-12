package com.jayesh.jayesh;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jayesh.jayesh.List_Product.Productlist_fragment;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    public static String subcategory;
    ListView listviewcategory;
    ProgressDialog progressd;
    //ProgressBar procategoryList;
    List<category> catList;
    TextView tvname,tv;
    FragmentManager fm;
    FragmentTransaction fx;
    String FinalJSonObject;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_fragment, container, false);
        listviewcategory = (ListView) v.findViewById(R.id.lvcategory);
        catList = new ArrayList<>();
        progressd = new ProgressDialog(getActivity());
        progressd.setCancelable(false);
        //procategoryList = (ProgressBar) v.findViewById(R.id.progressbarcategory);
        loadCatList();

        tvname = (TextView)v.findViewById(R.id.tvcategory);
        listviewcategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView)view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;
                    case 1:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;

                    case 2:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;

                    case 3:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;

                    case 4:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;

                    case 5:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;

                    case 6:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;

                    case 7:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;

                    case 8:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;

                    case 9:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;

                    case 10:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;

                    case 11:
                        fm=getFragmentManager();
                        fx=fm.beginTransaction();
                        tv = (TextView) view.findViewById(R.id.tvcategory);
                        subcategory=tv.getText().toString();
                        Toast.makeText(getActivity(),subcategory,Toast.LENGTH_LONG).show();
                        fx.replace(R.id.mainframe,new Productlist_fragment(),"plist").addToBackStack("tag");
                        fx.commit();
                        break;
                }
            }
        });
        return v;
    }

    private void loadCatList() {
          //making the progressbar visible
        progressd.setMessage("Loading..");
        progressd.show();
        //procategoryList.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET,MainActivity.JSON_URL, new Response.Listener<String>()
        {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressd.dismiss();
                        //procategoryList.setVisibility(View.INVISIBLE);
                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("categories");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject catObject = heroArray.getJSONObject(i);
                                category cat= new category(catObject.getString("subcat_name"));

                                //adding the hero to herolist
                                catList.add(cat);
                            }

                            //creating custom adapter object
                            adapter_categorylist adapter = new adapter_categorylist(catList, getActivity());

                            //adding the adapter to listview
                            listviewcategory.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getActivity(),"error"+ error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }
}
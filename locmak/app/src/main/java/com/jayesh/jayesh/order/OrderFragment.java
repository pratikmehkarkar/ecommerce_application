package com.jayesh.jayesh.order;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayesh.jayesh.ConfirmOrder.ConfirmOrder_fragment;
import com.jayesh.jayesh.R;
import com.jayesh.jayesh.cart.CartFragment;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shubham on 10/2/2017.
 */

public class OrderFragment extends android.app.Fragment {
    private EditText etname;
    private EditText etcontact;
    private EditText etemail;
    private EditText etaddress;
    private Button btncontinue;
    public static String usercontact="",uname,ucontact,uemail,uaddress;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.order_fragment,container,false);
        etname = (EditText) v.findViewById(R.id.etorderusername);
        etcontact = (EditText) v.findViewById(R.id.etordercontact);
        etemail = (EditText) v.findViewById(R.id.etorderemail);
        etaddress = (EditText) v.findViewById(R.id.etorderaddress);

        btncontinue = (Button) v.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uname=etname.getText().toString();
                ucontact=etcontact.getText().toString();
                uemail= etemail.getText().toString();
                uaddress=etaddress.getText().toString();

                if (uname !="" && ucontact != "" && uemail != "" &&  uaddress != "")
                {
                    Toast.makeText(getActivity(), uname+ ucontact+ uaddress+ uemail,Toast.LENGTH_SHORT).show();

                    FragmentManager fm=getFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.mainframe,new com.jayesh.jayesh.ConfirmOrder.ConfirmOrder_fragment()).addToBackStack("tag");
                    ft.commit();
                }
                else
                {
                    Toast.makeText(getActivity(),"enter all details",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}

package com.jayesh.jayesh.ConfirmOrder;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.jayesh.jayesh.order.OrderFragment;
import com.jayesh.jayesh.R;

import org.w3c.dom.Text;

/**
 * Created by Shubham on 12/21/2017.
 */

public class ConfirmOrder_fragment extends android.app.Fragment {
    TextView address, total, date, quantity;
    Button btnorder;
    View v;
OrderFragment of;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.confirmorder_fragment, container, false);
        address = (TextView) v.findViewById(R.id.tvshippingaddresslabel);
        total = (TextView) v.findViewById(R.id.tvtoatl);
        date = (TextView) v.findViewById(R.id.tvdeliverydate);
        quantity = (TextView) v.findViewById(R.id.tvquantity);
          Toast.makeText(getActivity(), com.jayesh.jayesh.order.OrderFragment.uname+ com.jayesh.jayesh.order.OrderFragment.ucontact+ com.jayesh.jayesh.order.OrderFragment.uaddress+ com.jayesh.jayesh.order.OrderFragment.uemail,Toast.LENGTH_SHORT);

        btnorder = (Button) v.findViewById(R.id.btnorder);
        return  v;
    }
}

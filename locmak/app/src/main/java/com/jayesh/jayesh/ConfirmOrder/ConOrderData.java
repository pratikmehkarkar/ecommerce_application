package com.jayesh.jayesh.ConfirmOrder;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shubham on 12/21/2017.
 */

public class ConOrderData {
    @SerializedName("product_id")
    String  productid;
    @SerializedName("name")
    String productname;
    @SerializedName("price")
    String price;
    @SerializedName("image_url")
    String imageurl;

    @SerializedName("cart_id")
    String cartid;

}

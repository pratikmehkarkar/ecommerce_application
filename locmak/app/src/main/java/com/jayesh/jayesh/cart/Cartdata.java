package com.jayesh.jayesh.cart;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shubham on 11/26/2017.
 */

public class Cartdata {
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


    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}

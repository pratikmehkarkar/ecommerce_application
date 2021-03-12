package com.jayesh.jayesh.List_Product;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shubham on 11/19/2017.
 */

public class Product {
    @SerializedName("product_id")
    String  productid;
    @SerializedName("name")
    String productname;
    @SerializedName("price")
    String price;
    @SerializedName("description")
    String desc;
   /* @SerializedName("instock")
    String instock;
    @SerializedName("offer")
    String offer;
    @SerializedName("color")
    String color;*/
    @SerializedName("image_url")
    String imageurl;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    /* public String getInstock() {
        return instock;
    }

    public void setInstock(String instock) {
        this.instock = instock;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }*/

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}

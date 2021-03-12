package com.jayesh.jayesh.offer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gopss on 17-Dec-17.
 */

public class OfferData {
    @SerializedName("name")
    String pname;
    @SerializedName("price")
    String originalprice;
    @SerializedName("offer_price")
    String offerprice;
    @SerializedName("product_id")
    String productid;
    @SerializedName("image_url")
    String imageurl;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(String originalprice) {
        this.originalprice = originalprice;
    }

    public String getOfferprice() {
        return offerprice;
    }

    public void setOfferprice(String offerprice) {
        this.offerprice = offerprice;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
}

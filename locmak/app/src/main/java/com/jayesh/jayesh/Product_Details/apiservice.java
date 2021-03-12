package com.jayesh.jayesh.Product_Details;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Shubham on 11/24/2017.
 */

public interface apiservice {

    @GET("new1.php")
    Call<List<product>> getProductdetails(@Query("product_id")String productid);
}


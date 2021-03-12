package com.jayesh.jayesh.List_Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Shubham on 11/19/2017.
 */

public interface Api_service {

    @GET("productlist2.php")
    Call<List<Product>> getproductlist(@Query("maincategory") Integer maincategory,
                                       @Query("subcategory") String subcategory);


}

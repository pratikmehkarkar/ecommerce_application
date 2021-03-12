package com.jayesh.jayesh.cart;

import com.jayesh.jayesh.List_Product.Product;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Shubham on 11/26/2017.
 */

public interface api_cart {

    @FormUrlEncoded
    @POST("insertcart.php")
    Call<CartDataInsert> insertCartData(@Field("contact") String contact,
                                        @Field("product_id") String product_id);


    @GET("cartlist.php")
    Call<List<Cartdata>> getcartlist(@Query("contact") String contact);

    @DELETE("removeproduct.php")
    Call<ResponseBody> removeitem(@Path("cartid") String cartid);
}

package com.jayesh.jayesh.order;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Shubham on 12/20/2017.
 */

public interface order_api {

    @FormUrlEncoded
    @POST("order.php")
    Call<OrderData> insertorder(@Query("usercontact") String usercontact,
                                @Query("ordername") String name,
                                @Query("email") String email,
                                @Query("contact") String contact,
                                @Query("address") String address);
}

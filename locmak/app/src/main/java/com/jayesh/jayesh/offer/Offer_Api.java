package com.jayesh.jayesh.offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gopss on 17-Dec-17.
 */

public interface Offer_Api {


    @GET("offerlist.php")
    Call<List<OfferData>> getofferlist(@Query("product_cat") String product_cat);
}

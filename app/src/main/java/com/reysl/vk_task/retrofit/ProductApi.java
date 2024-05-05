package com.reysl.vk_task.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductApi {
    @GET("products")
    Call<ProductResponse> getProducts(@Query("skip") int skip, @Query("limit") int limit);
}

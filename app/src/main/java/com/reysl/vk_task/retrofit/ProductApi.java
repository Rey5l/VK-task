package com.reysl.vk_task.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {
    @GET("products")
    Call<ProductResponse> getProducts();
}

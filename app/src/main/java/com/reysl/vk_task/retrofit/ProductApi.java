package com.reysl.vk_task.retrofit;

import com.reysl.vk_task.models.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApi {
//    @GET("products/{id}")
//    Call<Product> getProductById(@Path("id") int id);
    @GET("products")
    Call<ProductResponse> getProducts();
}

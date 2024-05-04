package com.reysl.vk_task;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reysl.vk_task.adapters.ProductAdapter;
import com.reysl.vk_task.models.Product;
import com.reysl.vk_task.retrofit.ProductApi;
import com.reysl.vk_task.retrofit.ProductResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;
    private final ArrayList<Product> products = new ArrayList<>();
    private ProductApi productApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recView = findViewById(R.id.recView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        productApi = retrofit.create(ProductApi.class);
        loadProducts();
        ProductAdapter productAdapter = new ProductAdapter(this, products);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(productAdapter);
    }
        private void loadProducts() {
            Call<ProductResponse> call = productApi.getProducts();
            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if (response.isSuccessful()) {
                        List<Product> productList = response.body().getProducts();
                        products.addAll(productList);
                        updateRecyclerView();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    }
    private void updateRecyclerView() {
        ProductAdapter productAdapter = new ProductAdapter(this, products);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(productAdapter);
    }

}

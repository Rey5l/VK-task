package com.reysl.vk_task;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reysl.vk_task.adapters.ProductAdapter;
import com.reysl.vk_task.models.Product;
import com.reysl.vk_task.retrofit.ProductApi;
import com.reysl.vk_task.retrofit.ProductResponse;

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
    private Button prevBtn, nextBtn, buyBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prevBtn = findViewById(R.id.prevPageButton);
        nextBtn = findViewById(R.id.nextPageButton);
        recView = findViewById(R.id.recView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        productApi = retrofit.create(ProductApi.class);
        loadProducts(skip, limit);
        ProductAdapter productAdapter = new ProductAdapter(this, products);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(productAdapter);
    }

        private void loadProducts(int skip, int limit) {
            Call<ProductResponse> call = productApi.getProducts(skip, limit);
            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if (response.isSuccessful()) {
                        List<Product> productList = response.body().getProducts();
                        products.clear();
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

    private int skip = 0;
    private int totalProducts = 100;
    private int currentPage = 1;
    private final int limit = 20;

    public void onPrevPageButtonClick(View view) {
        if (currentPage > 1) {
            skip -= limit;
            loadProducts(skip, limit);
            currentPage--;
            nextBtn.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Already on the first page", Toast.LENGTH_SHORT).show();
            prevBtn.setVisibility(View.GONE);
        }
    }

    public void onNextPageButtonClick(View view) {
        if (skip + limit < totalProducts) {
            skip += limit;
            loadProducts(skip, limit);
            currentPage++;
            prevBtn.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Already on the last page", Toast.LENGTH_SHORT).show();
            nextBtn.setVisibility(View.GONE);
        }
    }


}

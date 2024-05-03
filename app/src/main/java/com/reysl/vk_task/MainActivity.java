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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;
    private final ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recView = findViewById(R.id.recView);
        initializeData();
        ProductAdapter productAdapter = new ProductAdapter(this, products);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(productAdapter);
    }

    private void initializeData() {
        products.add(new Product("Iphone 11", "An Apple's smartphone", "$500"));
        products.add(new Product("Iphone 11", "An Apple's smartphone", "$500"));
        products.add(new Product("Iphone 11", "An Apple's smartphone", "$500"));
        products.add(new Product("Iphone 11", "An Apple's smartphone", "$500"));
        products.add(new Product("Iphone 11", "An Apple's smartphone", "$500"));
    }

}

package com.reysl.vk_task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.reysl.vk_task.models.Product;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private final ArrayList<Product> products = new ArrayList<Product>();
    private TextView brandName, productName, description, price;
    private ImageButton backBtn;
    private ImageView productImg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_page);
        brandName = findViewById(R.id.brand_name);
        productName = findViewById(R.id.product_name);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        backBtn = findViewById(R.id.backBtn);
        productImg = findViewById(R.id.image_pager);

        Product product = getIntent().getParcelableExtra("product");
        brandName.setText(product.getBrand());
        productName.setText(product.getTitle());
        description.setText(product.getDescription());
        price.setText(product.getPrice());
        Glide.with(ProductActivity.this).load(product.getThumbnail()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(productImg);
        System.out.println(product.getThumbnail());
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

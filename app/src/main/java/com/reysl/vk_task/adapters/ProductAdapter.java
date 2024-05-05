package com.reysl.vk_task.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.reysl.vk_task.MainActivity;
import com.reysl.vk_task.ProductActivity;
import com.reysl.vk_task.R;
import com.reysl.vk_task.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.title.setText(product.getTitle());
        holder.description.setText(product.getDescription());
        holder.price.setText(product.getPrice());
        holder.buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inflater.getContext(), ProductActivity.class);

                intent.putExtra("product", product);

                inflater.getContext().startActivity(intent);
            }
        });

        Glide.with(inflater.getContext()).load(product.getThumbnail()).fitCenter().into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, price;
        Button buyBtn;
        ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            price = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.productImage);
            buyBtn = view.findViewById(R.id.button_for_buy);
        }
    }
}

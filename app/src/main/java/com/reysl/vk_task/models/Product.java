package com.reysl.vk_task.models;
import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String title;
    private String description;
    private String price;
    private String thumbnail;
    private String brand;

    public Product (String title, String description, String price, String thumbnail, String brand) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
        this.brand = brand;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice() {
        this.price = price;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBrand() {return this.brand;}

    public void setBrand(String brand) {this.brand = brand;}
    protected Product(Parcel in) {
        brand = in.readString();
        title = in.readString();
        description = in.readString();
        price = in.readString();
        thumbnail = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeString(thumbnail);
    }

}

package com.example.androidtest.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dressItemsTable")
public class DressItem implements Parcelable {

    @ColumnInfo (name="image")
    private int img_src;

    @ColumnInfo (name="title")
    private String title;

    @ColumnInfo (name="alert")
    private String alert;

    @ColumnInfo (name="price")
    private int price;

    @ColumnInfo (name="oldprice")
    private int oldPrice;

    @ColumnInfo (name="stars")
    private int stars;

    @ColumnInfo (name="reviews")
    private int reviews;


    @PrimaryKey
    @NonNull
    private String id;

    public String getId() {
        return id;
    }

    public DressItem(String id, int img_src, String title, String alert, int price, int oldPrice, int stars, int reviews) {
        this.img_src = img_src;
        this.title = title;
        this.alert = alert;
        this.price = price;
        this.oldPrice = oldPrice;
        this.stars = stars;
        this.reviews = reviews;
        this.id=id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public String getAlert() {
        return alert;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public int getPrice() {
        return price;
    }

    public String getPriceText() {
        return "$ " + Integer.toString(price);
    }

    public String getOldPriceText() {
        if (oldPrice!=-1) {
        return "$ " + Integer.toString(oldPrice);} else return null;
    }

    public int getStars() {
        return stars;
    }

    public int getReviews() {
        return reviews;
    }

    public String getReviewsText() {
        return "("+ Integer.toString(reviews) +")";
    }

    public int getImg_src() {
        return img_src;
    }

    public void setImg_src(int img_src) {
        this.img_src = img_src;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(img_src);
        parcel.writeString(title);
        parcel.writeString(alert);
        parcel.writeInt(price);
        parcel.writeInt(oldPrice);
        parcel.writeInt(stars);
        parcel.writeInt(reviews);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected DressItem (Parcel in) {
        img_src = in.readInt();
        title = in.readString();
        alert = in.readString();
        price = in.readInt();
        oldPrice = in.readInt();
        stars = in.readInt();
        reviews = in.readInt();
    }

    public static final Creator<DressItem> CREATOR = new Creator<DressItem>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public DressItem createFromParcel(Parcel in) {
            return new DressItem(in);
        }

        @Override
        public DressItem[] newArray(int size) {
            return new DressItem[size];
        }
    };
}

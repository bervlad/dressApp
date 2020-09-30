package com.example.androidtest.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class DressItem implements Parcelable {
    private int img_src;
    private String title, alert;
    private int price, oldPrice, stars, reviews;
    private boolean liked;
    private int id;

    public int getId() {
        return id;
    }

    public DressItem(int id, int img_src, String title, String alert, int price, int oldPrice, int stars, int reviews, boolean liked) {
        this.img_src = img_src;
        this.title = title;
        this.alert = alert;
        this.price = price;
        this.oldPrice = oldPrice;
        this.stars = stars;
        this.reviews = reviews;
        this.liked=liked;
        this.id=id;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isLiked() {
        return liked;
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

    public String getPrice() {
        return "$ " + Integer.toString(price);
    }

    public String getOldPrice() {
        if (oldPrice!=-1) {
        return "$ " + Integer.toString(oldPrice);} else return null;
    }

    public int getStars() {
        return stars;
    }

    public String getReviews() {
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
        parcel.writeByte((byte) (liked ? 1 : 0));
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
        liked = in.readByte() != 0;
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

package com.example.androidtest.model;

public class DressItem {
    private int img_src;
    private String title, alert;
    private int price, oldPrice, stars, reviews;

    public DressItem(int img_src, String title, String alert, int price, int oldPrice, int stars, int reviews) {
        this.img_src = img_src;
        this.title = title;
        this.alert = alert;
        this.price = price;
        this.oldPrice = oldPrice;
        this.stars = stars;
        this.reviews = reviews;
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
}

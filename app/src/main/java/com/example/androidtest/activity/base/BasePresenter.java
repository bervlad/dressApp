package com.example.androidtest.activity.base;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

}

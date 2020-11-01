package com.example.androidtest.activity.screens.dressdetails;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.activity.base.BasePresenterClass;
import com.example.androidtest.activity.screens.dresschooser.DressChooser;
import com.example.androidtest.model.BasketItem;
import com.example.androidtest.model.DressItem;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class DressDetailsPresenter extends BasePresenterClass implements DressDetailsContract.Presenter {

    private DressDetailsContract.View view;

    public DressDetailsPresenter(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void purchaseClicked() {

        FirebaseUser currentUser = getUser();

        if (isUserLoggedIn(currentUser)) return;

        String size = view.getSpinnerSize();
        String color = view.getSpinnerColor();
        String quant = view.getQuant();
        DressItem dressItem = view.getDressItem();

        if (size.equals("Size")) {
            view.showToast("Please specify size");
            return;
        }
        if (color.equals("Color")) {
            view.showToast("Please specify color");
            return;
        }

        BasketItem item = new BasketItem(Objects.requireNonNull(currentUser.getEmail()),
                dressItem.getId(),
                size,
                color,
                Integer.parseInt(quant));
        getApp().getBasketItems().add(item);

        Intent explicitIntent = new Intent(getActivity(), DressChooser.class);
        getActivity().startActivity(explicitIntent);
    }

    private boolean isUserLoggedIn(FirebaseUser currentUser) {
        if (currentUser == null) {
            view.showToast("Please login");
            return true;
        }
        return false;
    }

    @Override
    public void takeView(DressDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}


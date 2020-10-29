package com.example.androidtest.utils;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidtest.R;
import com.example.androidtest.activity.screens.dresschooser.DressChooserContract;
import com.example.androidtest.activity.screens.dresschooser.DressChooserPresenter;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.listeners.OnDressItemClickListener;
import com.example.androidtest.model.DressItem;
import com.example.androidtest.model.UserDressItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {

    private ArrayList<DressItem> items;
    private Context ctx;
    private OnDressItemClickListener listener;
    private DressChooserContract.Presenter presenter;


    private ViewHolder mHolder;

//    final AppDatabase appDatabase;

    public ItemRecyclerAdapter(ArrayList<DressItem> items, Context ctx, DressChooserContract.Presenter presenter) {
        this.items = items;
        this.ctx = ctx;
        this.presenter = presenter;
//        this.appDatabase = database;
    }

    public void setListener(OnDressItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(view, viewHolder.getAdapterPosition());
                }
            }
        });
        return viewHolder;
    }

    public ArrayList<DressItem> getItems() {
        return items;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        mHolder = holder;

        Glide.with(holder.dressTypeImage).load(items.get(position).getUri()).placeholder(R.drawable.ic_account_search_outline).into(holder.dressTypeImage);

        holder.titleCard.setText(items.get(position).getTitle());
        holder.price.setText(items.get(position).getPriceText());
        holder.reviews.setText(items.get(position).getReviewsText());

        if (!presenter.isLoggedIn()) {
            holder.likedImage.setVisibility(View.GONE);
        } else {
            holder.likedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (appDatabase.userItemDao().getLikesForUser(mUser.getEmail()).contains(items.get(position).getId())) {
                        holder.likedImage.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_unpressed_like));
                        removeLike(items.get(position));
                    } else {
                        holder.likedImage.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_pressed_like));
                        addLike(items.get(position));
                    }
                }
            });

            presenter.showHeart(items.get(position).getId());

        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, position);
            }
        });

        if (items.get(position).getOldPriceText() != null) {
            holder.crossedPrice.setVisibility(View.VISIBLE);
            holder.crossedPrice.setText(items.get(position).getOldPriceText());
            holder.crossedPrice.setPaintFlags(holder.crossedPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.crossedPrice.setVisibility(View.GONE);
        }

        if (items.get(position).getAlert() != null) {
            holder.alert.setVisibility(View.VISIBLE);
            holder.alert.setText(items.get(position).getAlert());
        } else {
            holder.alert.setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < items.get(position).getStars(); i++) {
            holder.stars.get(i).setColorFilter(ContextCompat.getColor(ctx, R.color.star_filled));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dressTypeImage, likedImage;
        TextView titleCard, price, crossedPrice, reviews, alert;
        ArrayList<ImageView> stars;
        View container = itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dressTypeImage = itemView.findViewById(R.id.item_img);
            likedImage = itemView.findViewById(R.id.like);
            titleCard = itemView.findViewById(R.id.title_card);
            price = itemView.findViewById(R.id.price);
            crossedPrice = itemView.findViewById(R.id.crossed_price);
            reviews = itemView.findViewById(R.id.reviews);
            alert = itemView.findViewById(R.id.alert);
            stars = new ArrayList<ImageView>();
            for (int i = 0; i < 5; i++) {
                stars.add((ImageView) itemView.findViewById(itemView.getContext().getResources().
                        getIdentifier("star_" + Integer.toString(i + 1), "id", itemView.getContext().getPackageName())));
            }
        }
    }


    public void getmHolder(Boolean isPressed) {
        if (!isPressed) mHolder.likedImage.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_unpressed_like));
        else mHolder.likedImage.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_pressed_like));
    }
}

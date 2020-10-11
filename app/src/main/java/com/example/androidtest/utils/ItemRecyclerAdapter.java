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

import com.example.androidtest.R;
import com.example.androidtest.app.AndroidTest;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.database.UserData;
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
    FirebaseUser mUser;
    UserData userData;
    AppDatabase appDatabase;

    public ItemRecyclerAdapter(ArrayList<DressItem> items, Context ctx, AppDatabase database) {
        this.items = items;
        this.ctx = ctx;
        this.appDatabase = database;
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

        mUser = (FirebaseAuth.getInstance()).getCurrentUser();

        holder.dressTypeImage.setImageDrawable(ContextCompat.getDrawable(ctx, items.get(position).getImg_src()));
        holder.titleCard.setText(items.get(position).getTitle());
        holder.price.setText(items.get(position).getPriceText());
        holder.reviews.setText(items.get(position).getReviewsText());

        if (mUser==null) {
            holder.likedImage.setVisibility(View.GONE);
        }
        else {
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

                    //items.get(position).setLiked(!items.get(position).isLiked());
                }
            });

            Log.d ("TAG", "SET: " + appDatabase.userItemDao().getLikesForUser(mUser.getEmail()).toString());
            if (appDatabase.userItemDao().getLikesForUser(mUser.getEmail()).contains(items.get(position).getId())) {
                holder.likedImage.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_pressed_like));
            }
            else {
                holder.likedImage.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_unpressed_like));
            }
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view,position);
            }
        });


//
//        if (items.get(position).isLiked()) {
//            holder.likedImage.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_pressed_like));
//        }  else {
//            holder.likedImage.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_unpressed_like));
//        }

        if (items.get(position).getOldPriceText()!=null) {
            holder.crossedPrice.setVisibility(View.VISIBLE);
            holder.crossedPrice.setText(items.get(position).getOldPriceText());
            holder.crossedPrice.setPaintFlags(holder.crossedPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.crossedPrice.setVisibility(View.GONE);
        }

        if (items.get(position).getAlert()!=null) {
            holder.alert.setVisibility(View.VISIBLE);
            holder.alert.setText(items.get(position).getAlert());
        } else {
            holder.alert.setVisibility(View.INVISIBLE);
        }

        for (int i=0; i<items.get(position).getStars(); i++) {
            holder.stars.get(i).setColorFilter(ContextCompat.getColor (ctx, R.color.star_filled));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dressTypeImage, likedImage;
        TextView titleCard, price, crossedPrice, reviews, alert;
        ArrayList <ImageView> stars;
        View container = itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dressTypeImage = itemView.findViewById(R.id.item_img);
            likedImage = itemView.findViewById(R.id.like);
            titleCard=itemView.findViewById(R.id.title_card);
            price=itemView.findViewById(R.id.price);
            crossedPrice=itemView.findViewById(R.id.crossed_price);
            reviews=itemView.findViewById(R.id.reviews);
            alert=itemView.findViewById(R.id.alert);
            stars=new ArrayList<ImageView>();
            for (int i = 0; i<5; i++ ) {
                stars.add((ImageView)itemView.findViewById(itemView.getContext().getResources().
                        getIdentifier("star_"+Integer.toString(i+1), "id", itemView.getContext().getPackageName())));
            }
        }
    }

    public void addLike (DressItem item) {

//            userData.getItems(mUser.getEmail()).add(item.getId());
//            Log.d("TAG", "Added");

            //sql room Database insert
            appDatabase.userDressItemDao().insert(new UserDressItem(mUser.getEmail(), item.getId()));
            Log.d("TAG", "Added item " + item.getId() + " to database for user " + mUser.getEmail());
    }

    public void removeLike (DressItem item) {

            //userData.getItems(mUser.getEmail()).remove(item.getId());

            //sql room Database delete
            appDatabase.userDressItemDao().deleteLikeFromUser(mUser.getEmail(), item.getId());
            Log.d("TAG", "Deleted item " + item.getId() + " to database for user " + mUser.getEmail());

    }
}

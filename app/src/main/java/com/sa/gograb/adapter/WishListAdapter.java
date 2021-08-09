package com.sa.gograb.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.R;
import com.sa.gograb.adapter.interfaces.FavouriteListClickListener;
import com.sa.gograb.adapter.interfaces.OnWishlistClickInvoke;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.menus.MenuListActivity;
import com.sa.gograb.services.model.WishModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    public static final String TAG = "WishlistAdapter";

    private final List<WishModel> list;
    private final Activity activity;
    FavouriteListClickListener wishlistClickInvoke;

    public WishListAdapter(Activity activity, List<WishModel> list,FavouriteListClickListener wishlistClickInvoke) {
        this.list = list;
        this.activity = activity;
        this.wishlistClickInvoke = wishlistClickInvoke;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final WishModel model = list.get(position);

        if (GlobalFunctions.isNotNullValue(model.getFull_name() )) {
            holder.tv_sub_category_title.setText(model.getFull_name());
        } if (GlobalFunctions.isNotNullValue(model.getDistance())) {
            holder.tv_distance.setText(model.getDistance());
        }
        if (GlobalFunctions.isNotNullValue(model.getRating())) {
            holder.tv_total_rating.setText(model.getRating());
        }
        if (GlobalFunctions.isNotNullValue(model.getRating_count())) {
            holder.tv_rating_count.setText("("+model.getRating_count()+"+)");
        }
        /*if (GlobalFunctions.isNotNullValue(model.getDistance())) {
            holder.tv_category_name.setText(model.getDistance());
        }*/


        if (GlobalFunctions.isNotNullValue(model.getImage() )) {
            Picasso.with(activity).load(model.getImage()).placeholder(R.drawable.app_icon).into(holder.iv_product_image);
        }
        holder.iv_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlistClickInvoke.OnFavouriteClickListener(position,model);

            }
        });

        holder.cd_wishlist_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MenuListActivity.newInstance( activity, model );
                activity.startActivity( intent );

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product_image, iv_wishlist;
        TextView tv_total_rating, tv_rating_count,tv_sub_category_title,tv_distance,tv_category_name;
        CardView cd_wishlist_main;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_product_image = itemView.findViewById(R.id.iv_product_image);
            iv_wishlist = itemView.findViewById(R.id.iv_wishlist);
            tv_total_rating = itemView.findViewById(R.id.tv_total_rating);
            tv_rating_count = itemView.findViewById(R.id.tv_rating_count);
            tv_sub_category_title = itemView.findViewById(R.id.tv_sub_category_title);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            cd_wishlist_main = itemView.findViewById(R.id.cd_wishlist_main);

        }
    }}

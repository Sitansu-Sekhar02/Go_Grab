package com.sauthi.gograb.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sauthi.gograb.R;
import com.sauthi.gograb.adapter.interfaces.OnWishlistClickInvoke;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.menus.MenuListActivity;
import com.sauthi.gograb.services.model.CusineModel;
import com.sauthi.gograb.services.model.HomeSubCategoryModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeSubCategoryListAdapter extends RecyclerView.Adapter<HomeSubCategoryListAdapter.ViewHolder> {

    public static final String TAG = "HomeSubSectionListAdapter";

    private final List<HomeSubCategoryModel> list;
    private final Activity activity;
    OnWishlistClickInvoke wishlistClickInvoke;
    HomeCusineAdapter cusineAdapter;

    public HomeSubCategoryListAdapter(Activity activity, List<HomeSubCategoryModel> list, OnWishlistClickInvoke wishlistClickInvoke) {
        this.list = list;
        this.activity = activity;
        this.wishlistClickInvoke = wishlistClickInvoke;

    }

    @NonNull
    @Override
    public HomeSubCategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_sub_category_second_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSubCategoryListAdapter.ViewHolder holder, int position) {

        final HomeSubCategoryModel model = list.get(position);


        if (GlobalFunctions.isNotNullValue(model.getFull_name())) {
            holder.tv_category_title.setText(model.getFull_name());
        }

       /* if (model.getLatitude()!=null && model.getLongitude()!=null){
           holder.tv_distance.setText(GlobalFunctions.getDistanceFromAddress(activity,model.getLatitude(),model.getLongitude()));
        }*/
        if (GlobalFunctions.isNotNullValue(model.getDistance())) {
            holder.tv_distance.setText(model.getDistance() + " " + activity.getString(R.string.kms));
        }

        if (GlobalFunctions.isNotNullValue(model.getRating())) {
            holder.tv_total_rating.setText(model.getRating());
        }

        if (GlobalFunctions.isNotNullValue(model.getRating_count())) {
            holder.tv_rating_count.setText("(" + model.getRating_count() + "+)");
        }


        if (GlobalFunctions.isNotNullValue(model.getImage())) {
            Picasso.with(activity).load(model.getImage()).placeholder(R.drawable.lazy_load).into(holder.iv_sub_category);
        }

        if (GlobalFunctions.isNotNullValue(model.getWishlist())) {
            if (model.getWishlist().equalsIgnoreCase("0")) {
                holder.iv_wishlist.setImageResource(R.drawable.ic_favourite_grey);
            } else {
                holder.iv_wishlist.setImageResource(R.drawable.ic_favourite_red);
            }
        }

        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false);
        List<CusineModel> productList = new ArrayList<CusineModel>();

        if (model.getCusineListModel() != null && model.getCusineListModel().getCusineModels().size() > 0) {
            productList.clear();
            productList.addAll(model.getCusineListModel().getCusineModels());
        }

        if (productList.size() > 0) {
            cusineAdapter = new HomeCusineAdapter(activity, productList);
            holder.home_sub_cusine_rv.setLayoutManager(layoutManager);
            holder.home_sub_cusine_rv.setAdapter(cusineAdapter);

            holder.home_sub_cusine_rv.setVisibility(View.VISIBLE);
        } else {
            holder.home_sub_cusine_rv.setVisibility(View.GONE);
        }


        holder.iv_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlistClickInvoke.OnSubCategoryClickInvoke(position, model);

            }
        });

        holder.card_restro_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MenuListActivity.newInstance(activity, model);
                activity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_sub_category, iv_wishlist;
        TextView tv_category_title, tv_distance;
        TextView tv_total_rating, tv_rating_count, tv_category_name, tv_cusine, tv_category_third_name;
        RelativeLayout rl_sub_category_main;
        CardView card_restro_main;
        RecyclerView home_sub_cusine_rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_sub_category = itemView.findViewById(R.id.iv_sub_category);
            iv_wishlist = itemView.findViewById(R.id.iv_wishlist);

            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_total_rating = itemView.findViewById(R.id.tv_total_rating);
            tv_rating_count = itemView.findViewById(R.id.tv_rating_count);
            card_restro_main = itemView.findViewById(R.id.card_restro_main);
            tv_category_title = itemView.findViewById(R.id.tv_category_title);
            tv_cusine = itemView.findViewById(R.id.tv_cusine);
            home_sub_cusine_rv = itemView.findViewById(R.id.home_sub_cusine_rv);


        }
    }
}
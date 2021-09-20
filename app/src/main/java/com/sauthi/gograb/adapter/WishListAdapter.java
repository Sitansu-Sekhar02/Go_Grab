package com.sauthi.gograb.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sauthi.gograb.R;
import com.sauthi.gograb.adapter.interfaces.FavouriteListClickListener;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.menus.MenuListActivity;
import com.sauthi.gograb.services.model.CusineModel;
import com.sauthi.gograb.services.model.WishModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    public static final String TAG = "WishlistAdapter";

    private final List<WishModel> list;
    private final Activity activity;
    FavouriteListClickListener wishlistClickInvoke;
    HomeCusineAdapter cusineAdapter;


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


        if (GlobalFunctions.isNotNullValue(model.getImage() )) {
            Picasso.with(activity).load(model.getImage()).placeholder(R.drawable.lazy_load).into(holder.iv_product_image);
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

        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false);
        List<CusineModel> productList = new ArrayList<CusineModel>();

        if (model.getCusineListModel() != null && model.getCusineListModel().getCusineModels().size() > 0) {
            productList.clear();
            productList.addAll(model.getCusineListModel().getCusineModels());
        }

        if (productList.size() > 0) {
            cusineAdapter = new HomeCusineAdapter(activity, productList);
            holder.rv_favourites.setLayoutManager(layoutManager);
            holder.rv_favourites.setAdapter(cusineAdapter);

            holder.rv_favourites.setVisibility(View.VISIBLE);
        } else {
            holder.rv_favourites.setVisibility(View.GONE);
        }
    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product_image, iv_wishlist;
        TextView tv_total_rating, tv_rating_count,tv_sub_category_title,tv_distance,tv_category_name;
        CardView cd_wishlist_main;
        RecyclerView rv_favourites;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_product_image = itemView.findViewById(R.id.iv_product_image);
            iv_wishlist = itemView.findViewById(R.id.iv_wishlist);
            tv_total_rating = itemView.findViewById(R.id.tv_total_rating);
            tv_rating_count = itemView.findViewById(R.id.tv_rating_count);
            tv_sub_category_title = itemView.findViewById(R.id.tv_sub_category_title);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            cd_wishlist_main = itemView.findViewById(R.id.cd_wishlist_main);
            rv_favourites = itemView.findViewById(R.id.rv_favourites);

        }
    }}

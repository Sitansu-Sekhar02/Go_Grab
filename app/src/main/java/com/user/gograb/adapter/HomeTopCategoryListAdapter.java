package com.user.gograb.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
import com.user.gograb.AppController;
import com.user.gograb.R;
import com.user.gograb.adapter.interfaces.OnWishlistClickInvoke;
import com.user.gograb.global.GlobalFunctions;
import com.user.gograb.global.GlobalVariables;
import com.user.gograb.menus.MenuListActivity;
import com.user.gograb.services.model.AddressModel;
import com.user.gograb.services.model.CusineModel;
import com.user.gograb.services.model.HomeTopCategoryModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeTopCategoryListAdapter extends RecyclerView.Adapter<HomeTopCategoryListAdapter.ViewHolder> {

    public static final String TAG = "HomeSubCategoryListAdapter";

    private final List<HomeTopCategoryModel> list;
    private final Activity activity;
    OnWishlistClickInvoke wishlistClickInvoke;
    HomeCusineAdapter cusineAdapter;
    GlobalFunctions globalFunctions;
    Double distance;
    Double restaurant_lat=null;
    Double restaurant_long=null;
    Double user_lat;
    Double user_long;
    LatLng latlng_user,latlng_restro;



    public HomeTopCategoryListAdapter(Activity activity, List<HomeTopCategoryModel> list, OnWishlistClickInvoke wishlistClickInvoke) {
        this.list = list;
        this.activity = activity;
        this.wishlistClickInvoke = wishlistClickInvoke;
        globalFunctions = AppController.getInstance().getGlobalFunctions();

    }

    @NonNull
    @Override
    public HomeTopCategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_sub_category_list_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeTopCategoryListAdapter.ViewHolder holder, int position) {
        final HomeTopCategoryModel model = list.get(position);

        if (GlobalFunctions.isNotNullValue(model.getFull_name())) {
            holder.tv_category_title.setText(model.getFull_name());
        }

        if (model.getLatitude()!=null && model.getLongitude()!=null) {
            restaurant_lat= Double.valueOf(model.getLatitude());
            restaurant_long= Double.valueOf(model.getLongitude());

        }

        AddressModel addressModel=GlobalFunctions.getAddress(activity);
        Log.d("addr11111",""+addressModel);
        if (addressModel!=null && addressModel.getLatitude()!=null && addressModel.getLongitude()!=null ){
            user_lat= Double.valueOf(addressModel.getLatitude());
            user_long= Double.valueOf(addressModel.getLongitude());

            //21.1662035, 86.7578215
            latlng_user = new LatLng(user_lat, user_long);
            latlng_restro = new LatLng(restaurant_lat, restaurant_long);
            distance = SphericalUtil.computeDistanceBetween(latlng_user, latlng_restro);

        }




        if (distance!=null) {
            String total_distance= String.format("%.2f", distance / 1000);

            holder.tv_distance.setText(total_distance+" "+activity.getString(R.string.kms));

        }

/*
        if (model.getLatitude()!=null && model.getLongitude()!=null){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    holder.tv_distance.setText(GlobalFunctions.getDistanceFromAddress(activity,model.getLatitude(),model.getLongitude()));
                }
            });

        }*/

        /*if (GlobalFunctions.isNotNullValue(model.getDistance() )) {
            holder.tv_distance.setText(model.getDistance()+" "+activity.getString(R.string.kms));
        }
*/


        if (GlobalFunctions.isNotNullValue(model.getRating())) {
            holder.tv_total_rating.setText(model.getRating());
        }

        if (GlobalFunctions.isNotNullValue(model.getRating_count())) {
            holder.tv_rating_count.setText("("+model.getRating_count()+"+)");
        }


        if (GlobalFunctions.isNotNullValue(model.getImage())) {
            Picasso.with(activity).load(model.getImage()).placeholder(R.drawable.lazy_load).into(holder.iv_category_icon);
        }
        if (GlobalFunctions.isNotNullValue(model.getWishlist())){
            if (model.getWishlist().equalsIgnoreCase("0")){
                holder.iv_wishlist.setImageResource(R.drawable.ic_favourite_grey);
            }else{
                holder.iv_wishlist.setImageResource(R.drawable.ic_favourite_red);
            }
        }


        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false);
        List<CusineModel> productList = new ArrayList<CusineModel>();

        if (model.getCusineListModel() != null && model.getCusineListModel().getCusineModels().size() > 0) {
            productList.clear();
            productList.addAll(model.getCusineListModel().getCusineModels());
        }

        if (productList.size() > 0) {
            cusineAdapter = new HomeCusineAdapter(activity, productList);
            holder.home_cusine_rv.setLayoutManager(layoutManager);
            holder.home_cusine_rv.setAdapter(cusineAdapter);

            holder.home_cusine_rv.setVisibility(View.VISIBLE);
        } else {
            holder.home_cusine_rv.setVisibility(View.GONE);
        }

        holder.iv_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlistClickInvoke.OnClickInvoke(position,model);

            }
        });

        holder.card_restro_main.setOnClickListener(new View.OnClickListener() {
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
        ImageView iv_category_icon,iv_wishlist;
        TextView tv_category_title,tv_distance;
        TextView tv_total_rating,tv_rating_count,tv_cusine_one,tv_cusine_two,tv_cusine_three;
        RelativeLayout rl_sub_category_main;
        CardView card_restro_main;
        RecyclerView home_cusine_rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_category_icon = itemView.findViewById(R.id.iv_category_icon);
            iv_wishlist = itemView.findViewById(R.id.iv_wishlist);

            tv_category_title = itemView.findViewById(R.id.tv_category_title);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_total_rating = itemView.findViewById(R.id.tv_total_rating);
            tv_rating_count = itemView.findViewById(R.id.tv_rating_count);
            tv_cusine_one = itemView.findViewById(R.id.tv_cusine_one);
            tv_cusine_two = itemView.findViewById(R.id.tv_cusine_two);
            tv_cusine_three = itemView.findViewById(R.id.tv_cusine_three);
            rl_sub_category_main = itemView.findViewById(R.id.rl_sub_category_main);
            card_restro_main = itemView.findViewById(R.id.card_restro_main);
            home_cusine_rv = itemView.findViewById(R.id.home_cusine_rv);



        }
    }
}

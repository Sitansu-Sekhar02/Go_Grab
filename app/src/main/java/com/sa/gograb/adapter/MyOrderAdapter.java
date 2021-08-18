package com.sa.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.model.CusineModel;
import com.sa.gograb.services.model.OrderDetailModel;
import com.sa.gograb.services.model.OrderModel;
import com.sa.gograb.services.model.TrendingMenuModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    public static final String TAG = "MyOrderAdapter";

    private final List<OrderModel> list;
    private final Activity activity;

    public MyOrderAdapter(Activity activity, List<OrderModel> list) {
        this.list = list;
        this.activity = activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final OrderModel model = list.get(position);

        if (GlobalFunctions.isNotNullValue(model.getRest_full_name())) {
            holder.tv_item_title.setText(model.getRest_full_name());
        }
        if (GlobalFunctions.isNotNullValue(model.getRest_logo())) {
            Picasso.with(activity).load(model.getRest_logo()).placeholder(R.drawable.image).into(holder.iv_restaurant);
        }
        if (GlobalFunctions.isNotNullValue(model.getRating())) {
            holder.tv_ratings.setText(model.getRating());
        }
        if (GlobalFunctions.isNotNullValue(model.getDistance())) {
            holder.tv_distance.setText(model.getDistance());
        }
        if (GlobalFunctions.isNotNullValue(model.getGrand_total())) {
            holder.tv_total_price.setText(model.getGrand_total());
        }
        if (GlobalFunctions.isNotNullValue(model.getCurrency())) {
            holder.tv_currency.setText(model.getCurrency());
        }
        if (GlobalFunctions.isNotNullValue(model.getRating_count())) {
            holder.tv_rating_count.setText("("+model.getRating_count()+")");
        }
        if (GlobalFunctions.isNotNullValue(model.getCreated_on())) {
            holder.tv_order_date.setText(GlobalFunctions.getDateFormat(model.getCreated_on()));
        }


        List<String> productList = new ArrayList<String>();

        if (model.getOrder_details() != null && model.getOrder_details().getOrderDetailModels().size() > 0) {
            productList.clear();
            productList.addAll(model.getOrder_details().getNames());
        }

        if (productList.size() > 0) {
            holder.prolist_tv.setText(GlobalFunctions.getStringFromList(productList));
            holder.prolist_tv.setVisibility(View.VISIBLE);
        } else {
            holder.prolist_tv.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv_restaurant;
        TextView myTextView,tv_item_title,tv_order_date,tv_ratings,tv_rating_count,tv_distance,tv_item_name,tv_currency,tv_total_price,prolist_tv;
        RecyclerView order_rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tv_item_name);
            iv_restaurant = itemView.findViewById(R.id.iv_restaurant);
            tv_item_title = itemView.findViewById(R.id.tv_item_title);
            tv_order_date = itemView.findViewById(R.id.tv_order_date);
            tv_ratings = itemView.findViewById(R.id.tv_ratings);
            tv_rating_count = itemView.findViewById(R.id.tv_rating_count);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_item_name = itemView.findViewById(R.id.tv_item_name);
            tv_currency = itemView.findViewById(R.id.tv_currency);
            tv_total_price = itemView.findViewById(R.id.tv_total_price);
            order_rv = itemView.findViewById(R.id.order_rv);
            prolist_tv = itemView.findViewById(R.id.prolist_tv);


        }
    }
}

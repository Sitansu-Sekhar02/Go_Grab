package com.sa.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.model.OrderDetailModel;
import com.sa.gograb.services.model.OrderModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderSubAdapter extends RecyclerView.Adapter<OrderSubAdapter.ViewHolder> {

    public static final String TAG = "AllOrderAdapter";

    private final List<OrderDetailModel> list;
    private final Activity activity;
    public OrderSubAdapter(Activity activity, List<OrderDetailModel> list) {
        this.list = list;
        this.activity = activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_sub_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final OrderDetailModel model = list.get(position);


          if (GlobalFunctions.isNotNullValue(model.getName())) {
                holder.tv_item_name.setText(model.getName());
            }
            if (GlobalFunctions.isNotNullValue(model.getPrice())) {
                holder.tv_total_price.setText(model.getPrice());
            }
            if (GlobalFunctions.isNotNullValue(model.getCurrency())) {
                holder.tv_currency.setText(model.getCurrency());
            }
            holder.tv_reorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView,tv_item_title,tv_item_name,tv_currency,tv_total_price,tv_reorder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_reorder = itemView.findViewById(R.id.tv_reorder);
            tv_item_name = itemView.findViewById(R.id.tv_item_name);
            tv_currency = itemView.findViewById(R.id.tv_currency);
            tv_total_price = itemView.findViewById(R.id.tv_total_price);


        }
    }
}

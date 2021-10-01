package com.user.gograb.adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.gograb.AppController;
import com.user.gograb.R;
import com.user.gograb.adapter.interfaces.CartClickListener;
import com.user.gograb.global.GlobalFunctions;
import com.user.gograb.services.model.TrendingMenuModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

    public static final String TAG = "MenuListAdapter";

    private final List<TrendingMenuModel> list;
    private final Activity activity;
    String minimumQuantity = "0";
    private CartClickListener listener;

    String qty;

    GlobalFunctions globalFunctions;

    public MenuListAdapter(Activity activity, List<TrendingMenuModel> list,CartClickListener listener) {
        this.list = list;
        this.activity = activity;
        globalFunctions = AppController.getInstance().getGlobalFunctions();
        this.listener = listener;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_list_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TrendingMenuModel model = list.get(position);

        if (GlobalFunctions.isNotNullValue(model.getName())) {
            holder.item_title_tv.setText(model.getName());
        }
        if (GlobalFunctions.isNotNullValue(model.getMenu_type())) {
            holder.tv_menu_type.setText(model.getMenu_type());
        }
        if (GlobalFunctions.isNotNullValue(model.getCurrency())) {
            holder.tv_currency.setText(model.getCurrency());
        }
        if (GlobalFunctions.isNotNullValue(model.getPrice())) {
            holder.unit_price_tv.setText(model.getPrice());
            //holder.unit_price_tv.setPaintFlags( holder.unit_price_tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG );

        }
        if (GlobalFunctions.isNotNullValue(model.getOffer_price())) {
            holder.tv_offer_price.setText(model.getOffer_price());
            holder.unit_price_tv.setPaintFlags( holder.unit_price_tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG );
        }

        if (GlobalFunctions.isNotNullValue(model.getDescription())) {
            holder.product_description_tv.setText(globalFunctions.getHTMLString(model.getDescription()));
        }
        if (GlobalFunctions.isNotNullValue(model.getFood_type())) {
            if (model.getFood_type().equalsIgnoreCase("1")){

                holder.iv_menu_food_type.setImageResource(R.drawable.ic_veg_menu_item);

            }else {
                holder.iv_menu_food_type.setImageResource(R.drawable.ic_nonveg_menu);

            }
        }

        if (GlobalFunctions.isNotNullValue(model.getImage())) {
            Picasso.with(activity).load(model.getImage()).placeholder(R.drawable.lazy_load).into(holder.product_iv);
        }

        if (GlobalFunctions.isNotNullValue(model.getCart_count())) {
            holder.quantity_tv.setText(model.getCart_count());

            holder.ll_add_item.setVisibility(View.VISIBLE);
            holder.tv_add_item.setVisibility(View.GONE);
        }else {
            holder.ll_add_item.setVisibility(View.GONE);
            holder.tv_add_item.setVisibility(View.VISIBLE);
        }

        holder.tv_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ll_add_item.setVisibility(View.VISIBLE);
                holder.tv_add_item.setVisibility(View.GONE);

                qty=holder.quantity_tv.getText().toString();
                listener.OnCartInvoked( model,qty);
            }
        });


        holder.add_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityCounter(model, holder.quantity_tv, true);

            }
        });

        holder.minus_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityCounter(model, holder.quantity_tv, false);

            }
        });

    }

    private void quantityCounter(TrendingMenuModel cartDetailModel,TextView qty_ev, boolean isAddition) {
        String minimumQuantity = "0";
        try {
            if (qty_ev != null) {
                String val = qty_ev.getText().toString().trim();

                int qty = Integer.parseInt(val == null && val.equalsIgnoreCase("") ? minimumQuantity : val);
                if (isAddition) {
                    qty = qty + 1;
                } else {
                    if (qty > Integer.parseInt(minimumQuantity)) {
                        qty = qty - 1;
                    }
                }

                if (qty!=0) {
                    qty_ev.setText(qty + "");
                }
                listener.OnCartInvoked(cartDetailModel,qty+"");

            }
        } catch (Exception e) {
            GlobalFunctions.displayErrorDialog(activity, activity.getString(R.string.somethingWentWrong));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product_iv,minus_iv,add_iv,iv_menu_food_type;
        TextView tv_category_name,item_title_tv,unit_price_tv,product_description_tv,tv_menu_type,tv_add_item,quantity_tv,tv_currency,tv_offer_price;
        LinearLayout ll_add_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_iv = itemView.findViewById(R.id.product_iv);
            minus_iv = itemView.findViewById(R.id.minus_iv);
            add_iv = itemView.findViewById(R.id.add_iv);
            iv_menu_food_type = itemView.findViewById(R.id.iv_menu_food_type);

            item_title_tv = itemView.findViewById(R.id.item_title_tv);
            unit_price_tv = itemView.findViewById(R.id.unit_price_tv);
            product_description_tv = itemView.findViewById(R.id.product_description_tv);
            tv_menu_type = itemView.findViewById(R.id.tv_menu_type);
            tv_currency = itemView.findViewById(R.id.tv_currency);
            tv_add_item = itemView.findViewById(R.id.tv_add_item);
            tv_offer_price = itemView.findViewById(R.id.tv_offer_price);
            ll_add_item = itemView.findViewById(R.id.ll_add_item);
            quantity_tv = itemView.findViewById(R.id.quantity_tv);

        }
    }
}

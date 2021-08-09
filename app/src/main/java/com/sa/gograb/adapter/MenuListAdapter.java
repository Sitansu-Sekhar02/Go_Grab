package com.sa.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.AppController;
import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.model.TrendingMenuModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

    public static final String TAG = "MenuListAdapter";

    private final List<TrendingMenuModel> list;
    private final Activity activity;
    String minimumQuantity = "0";

    GlobalFunctions globalFunctions;

    public MenuListAdapter(Activity activity, List<TrendingMenuModel> list) {
        this.list = list;
        this.activity = activity;
        globalFunctions = AppController.getInstance().getGlobalFunctions();

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
        if (GlobalFunctions.isNotNullValue(model.getPrice())) {
            holder.unit_price_tv.setText(activity.getString(R.string.sar) +model.getPrice());
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
            Picasso.with(activity).load(model.getImage()).placeholder(R.drawable.image).into(holder.product_iv);
        }

        holder.tv_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ll_add_item.setVisibility(View.VISIBLE);
                holder.tv_add_item.setVisibility(View.GONE);
            }
        });


        holder.add_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityCounter(holder.quantity_tv, holder.minus_iv, true, position, model);
            }
        });

        holder.minus_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.quantity_tv != null) {
                    String val = holder.quantity_tv.getText().toString();
                    int qty = Integer.parseInt(val == null && val.equalsIgnoreCase("") ? minimumQuantity : val);
                    if (qty > Integer.parseInt(minimumQuantity)) {
                        qty--;
                        holder.quantity_tv.setEnabled(true);
                        holder.quantity_tv.setClickable(true);
                        quantityCounter(holder.quantity_tv, holder.minus_iv, false, position, model);
                    } else if (qty == Integer.parseInt(minimumQuantity)) {
                        //disable sub button...
                        holder.quantity_tv.setEnabled(false);
                        holder.quantity_tv.setClickable(false);
                    }
                }

            }
        });





    }

    private void quantityCounter(TextView qty_ev, ImageView subIv, boolean isAddition, int position, TrendingMenuModel model) {
        if (qty_ev != null) {
            String val = qty_ev.getText().toString();

           /* if (isAddition) {
                if (val.equalsIgnoreCase(availableQuantity)) {
                    globalFunctions.displayMessaage(activity, subIv, activity.getString(R.string.available_quantity_is) + " " + availableQuantity + ". " + activity.getString(R.string.no_more_quantity_is_available));
                    return;
                }
            }*/

            int qty = Integer.parseInt(val == null && val.equalsIgnoreCase("") ? minimumQuantity : val);
            if (isAddition) {
                qty = qty + 1;
                //update quantity....send quantity as qty...
                //listener.OnCartItemClickInvoke(position, qty, model);
            } else {
                if (qty > Integer.parseInt(minimumQuantity)) {
                    qty--;
                    //update quantity....send quantity as (-1)...
                    /* int tempQty = -1;*/
                   // listener.OnCartItemClickInvoke(position, qty, model);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product_iv,minus_iv,add_iv,iv_menu_food_type;
        TextView tv_category_name,item_title_tv,unit_price_tv,product_description_tv,tv_menu_type,tv_add_item,quantity_tv;
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
            tv_add_item = itemView.findViewById(R.id.tv_add_item);
            ll_add_item = itemView.findViewById(R.id.ll_add_item);
            quantity_tv = itemView.findViewById(R.id.quantity_tv);

        }
    }
}

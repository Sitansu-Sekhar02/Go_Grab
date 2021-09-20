package com.sauthi.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sauthi.gograb.R;
import com.sauthi.gograb.adapter.interfaces.OnCartInvokeListener;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.services.model.CartDetailModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public static final String TAG = "CartAdapter";

    private final List<CartDetailModel> list;
    private final Activity activity;
    String qty;
    private OnCartInvokeListener listener;


    public CartAdapter(Activity activity, List<CartDetailModel> list, OnCartInvokeListener listener) {
        this.list = list;
        this.activity = activity;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CartDetailModel model = list.get(position);

        if (GlobalFunctions.isNotNullValue(model.getMenu_title())) {
            holder.tv_item_name.setText(model.getMenu_title());
        }
        if (GlobalFunctions.isNotNullValue(model.getUnits())) {
            holder.unit_price_tv.setText(model.getUnits());
        }
        if (GlobalFunctions.isNotNullValue(model.getCurrency())) {
            holder.tv_currency.setText(model.getCurrency());
        }
        if (GlobalFunctions.isNotNullValue(model.getQuantity())) {
            holder.quantity_tv.setText(model.getQuantity());
        }


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

    private void quantityCounter(CartDetailModel cartDetailModel, TextView qty_ev, boolean isAddition) {
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

                if (qty != 0) {
                    qty_ev.setText(qty + "");
                }
                listener.OnCartListListener(cartDetailModel, qty + "");

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
        TextView tv_item_name, unit_price_tv, tv_currency, quantity_tv;
        ImageView product_iv, minus_iv, add_iv, iv_menu_food_type;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item_name = itemView.findViewById(R.id.tv_item_name);
            unit_price_tv = itemView.findViewById(R.id.unit_price_tv);
            tv_currency = itemView.findViewById(R.id.tv_currency);
            quantity_tv = itemView.findViewById(R.id.quantity_tv);
            minus_iv = itemView.findViewById(R.id.minus_iv);
            add_iv = itemView.findViewById(R.id.add_iv);

        }
    }
}

package com.sa.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.AppController;
import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.model.MenuCatModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryMenuSubListAdapter extends RecyclerView.Adapter<CategoryMenuSubListAdapter.ViewHolder> {

    public static final String TAG = "CategoryMenuListAdapter";

    private final List<MenuCatModel> list;
    private final Activity activity;
    GlobalFunctions globalFunctions;

    public CategoryMenuSubListAdapter(Activity activity, List<MenuCatModel> list) {
        this.list = list;
        this.activity = activity;
        globalFunctions = AppController.getInstance().getGlobalFunctions();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_menu_sub_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MenuCatModel model = list.get(position);

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


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product_iv,minus_iv,add_iv,iv_menu_food_type,iv_category_dropDown;
        TextView tv_category_name,item_title_tv,unit_price_tv,product_description_tv,tv_menu_type,tv_category_menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_iv = itemView.findViewById(R.id.product_iv);
            minus_iv = itemView.findViewById(R.id.minus_iv);
            add_iv = itemView.findViewById(R.id.add_iv);
            iv_menu_food_type = itemView.findViewById(R.id.iv_menu_food_type);
            iv_category_dropDown = itemView.findViewById(R.id.iv_category_dropDown);

            item_title_tv = itemView.findViewById(R.id.item_title_tv);
            unit_price_tv = itemView.findViewById(R.id.unit_price_tv);
            product_description_tv = itemView.findViewById(R.id.product_description_tv);
            tv_menu_type = itemView.findViewById(R.id.tv_menu_type);
            tv_category_menu = itemView.findViewById(R.id.tv_category_menu);

        }
    }
}
